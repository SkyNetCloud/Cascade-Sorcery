package ca.skynetcloud.cascadesorcery.procedures;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.Entity;

import java.util.Map;

import ca.skynetcloud.cascadesorcery.item.SwordofEndlessRestlessItem;
import ca.skynetcloud.cascadesorcery.entity.ShadowGlomeEntity;
import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class PlayerHasHitEntityProcedure extends CascadeSorceryModElements.ModElement {
	public PlayerHasHitEntityProcedure(CascadeSorceryModElements instance) {
		super(instance, 54);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure PlayerHasHitEntity!");
			return false;
		}
		if (dependencies.get("x") == null) {
			System.err.println("Failed to load dependency x for procedure PlayerHasHitEntity!");
			return false;
		}
		if (dependencies.get("y") == null) {
			System.err.println("Failed to load dependency y for procedure PlayerHasHitEntity!");
			return false;
		}
		if (dependencies.get("z") == null) {
			System.err.println("Failed to load dependency z for procedure PlayerHasHitEntity!");
			return false;
		}
		if (dependencies.get("world") == null) {
			System.err.println("Failed to load dependency world for procedure PlayerHasHitEntity!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		IWorld world = (IWorld) dependencies.get("world");
		if ((((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(SwordofEndlessRestlessItem.block, (int) (1)))
				: false) && (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHealth() : -1) < 5))) {
			if (world instanceof World && !world.getWorld().isRemote) {
				Entity entityToSpawn = new ShadowGlomeEntity.CustomEntity(ShadowGlomeEntity.entity, world.getWorld());
				entityToSpawn.setLocationAndAngles(x, y, z, (float) 0, (float) 0);
				if (entityToSpawn instanceof MobEntity)
					((MobEntity) entityToSpawn).onInitialSpawn(world, world.getDifficultyForLocation(new BlockPos(entityToSpawn)),
							SpawnReason.MOB_SUMMONED, (ILivingEntityData) null, (CompoundNBT) null);
				world.addEntity(entityToSpawn);
			}
			return (true);
		}
		return (false);
	}
}
