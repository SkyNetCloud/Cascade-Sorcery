
package ca.skynetcloud.cascadesorcery.entity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.network.NetworkHooks;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.DamageSource;
import net.minecraft.network.IPacket;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.RestrictSunGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.MoveTowardsVillageGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.Entity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.client.renderer.entity.model.VillagerModel;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.block.material.Material;

import com.google.common.collect.ImmutableMap;

import ca.skynetcloud.cascadesorcery.procedures.PlayerHasHitEntityProcedure;
import ca.skynetcloud.cascadesorcery.itemgroup.CascadeSorceryTabItemGroup;
import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class ShadowVillagerEntity extends CascadeSorceryModElements.ModElement {
	public static EntityType entity = null;
	public ShadowVillagerEntity(CascadeSorceryModElements instance) {
		super(instance, 38);
		FMLJavaModLoadingContext.get().getModEventBus().register(this);
	}

	@Override
	public void initElements() {
		entity = (EntityType.Builder.<CustomEntity>create(CustomEntity::new, EntityClassification.CREATURE).setShouldReceiveVelocityUpdates(true)
				.setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(CustomEntity::new).size(0.6f, 1.95f)).build("shadow_villager")
						.setRegistryName("shadow_villager");
		elements.entities.add(() -> entity);
		elements.items.add(() -> new SpawnEggItem(entity, -6710887, -13421773, new Item.Properties().group(CascadeSorceryTabItemGroup.tab))
				.setRegistryName("shadow_villager"));
	}

	@Override
	public void init(FMLCommonSetupEvent event) {
		for (Biome biome : ForgeRegistries.BIOMES.getValues()) {
			boolean biomeCriteria = false;
			if (ForgeRegistries.BIOMES.getKey(biome).equals(new ResourceLocation("cascade_sorcery:shadow_biome")))
				biomeCriteria = true;
			if (!biomeCriteria)
				continue;
			biome.getSpawns(EntityClassification.CREATURE).add(new Biome.SpawnListEntry(entity, 20, 4, 4));
		}
		EntitySpawnPlacementRegistry.register(entity, EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos,
						random) -> (world.getBlockState(pos.down()).getMaterial() == Material.ORGANIC && world.getLightSubtracted(pos, 0) > 8));
	}

	@SubscribeEvent
	@OnlyIn(Dist.CLIENT)
	public void registerModels(ModelRegistryEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(entity, renderManager -> new MobRenderer(renderManager, new VillagerModel(0), 0.5f) {
			@Override
			public ResourceLocation getEntityTexture(Entity entity1) {
				return new ResourceLocation("cascade_sorcery:textures/shadow_villager.png");
			}
		});
	}
	public static class CustomEntity extends VillagerEntity {
		public CustomEntity(FMLPlayMessages.SpawnEntity packet, World world) {
			this(entity, world);
		}

		public CustomEntity(EntityType<CustomEntity> type, World world) {
			super(type, world);
			experienceValue = 0;
			setNoAI(false);
			setCustomName(new StringTextComponent("Shadow Villager"));
			setCustomNameVisible(true);
		}

		@Override
		public IPacket<?> createSpawnPacket() {
			return NetworkHooks.getEntitySpawningPacket(this);
		}

		@Override
		protected void registerGoals() {
			super.registerGoals();
			this.goalSelector.addGoal(1, new PanicGoal(this, 1));
			this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, PlayerEntity.class, false, false) {
				@Override
				public boolean shouldExecute() {
					double x = CustomEntity.this.getPosX();
					double y = CustomEntity.this.getPosY();
					double z = CustomEntity.this.getPosZ();
					Entity entity = CustomEntity.this;
					return super.shouldExecute() && PlayerHasHitEntityProcedure
							.executeProcedure(ImmutableMap.of("entity", entity, "x", x, "y", y, "z", z, "world", world));
				}
			});
			this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, ServerPlayerEntity.class, false, false) {
				@Override
				public boolean shouldExecute() {
					double x = CustomEntity.this.getPosX();
					double y = CustomEntity.this.getPosY();
					double z = CustomEntity.this.getPosZ();
					Entity entity = CustomEntity.this;
					return super.shouldExecute() && PlayerHasHitEntityProcedure
							.executeProcedure(ImmutableMap.of("entity", entity, "x", x, "y", y, "z", z, "world", world));
				}
			});
			this.goalSelector.addGoal(4, new RestrictSunGoal(this));
			this.goalSelector.addGoal(5, new RandomWalkingGoal(this, 0.6));
			this.goalSelector.addGoal(6, new MoveTowardsVillageGoal(this, 0.5));
			this.goalSelector.addGoal(7, new OpenDoorGoal(this, true));
			this.goalSelector.addGoal(8, new OpenDoorGoal(this, false));
			this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
			this.goalSelector.addGoal(10, new AvoidEntityGoal(this, MobEntity.class, (float) 6, 1, 1.2));
			this.goalSelector.addGoal(11, new LookAtGoal(this, PlayerEntity.class, (float) 6));
			this.goalSelector.addGoal(12, new LookAtGoal(this, ServerPlayerEntity.class, (float) 6));
			this.goalSelector.addGoal(13, new SwimGoal(this));
		}

		@Override
		public CreatureAttribute getCreatureAttribute() {
			return CreatureAttribute.UNDEFINED;
		}

		protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
			super.dropSpecialItems(source, looting, recentlyHitIn);
		}

		@Override
		public net.minecraft.util.SoundEvent getAmbientSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cascade_sorcery:mod_sound"));
		}

		@Override
		public net.minecraft.util.SoundEvent getHurtSound(DamageSource ds) {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(""));
		}

		@Override
		public net.minecraft.util.SoundEvent getDeathSound() {
			return (net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("cascade_sorcery:death_sound"));
		}

		@Override
		protected void registerAttributes() {
			super.registerAttributes();
			if (this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED) != null)
				this.getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3);
			if (this.getAttribute(SharedMonsterAttributes.MAX_HEALTH) != null)
				this.getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10);
			if (this.getAttribute(SharedMonsterAttributes.ARMOR) != null)
				this.getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0);
			if (this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE) == null)
				this.getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
			this.getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3);
		}
	}
}
