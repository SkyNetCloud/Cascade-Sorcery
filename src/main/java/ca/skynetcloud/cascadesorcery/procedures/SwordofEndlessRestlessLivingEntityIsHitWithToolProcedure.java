package ca.skynetcloud.cascadesorcery.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.enchantment.Enchantments;

import java.util.Map;

import ca.skynetcloud.cascadesorcery.item.SwordofEndlessRestlessItem;
import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class SwordofEndlessRestlessLivingEntityIsHitWithToolProcedure extends CascadeSorceryModElements.ModElement {
	public SwordofEndlessRestlessLivingEntityIsHitWithToolProcedure(CascadeSorceryModElements instance) {
		super(instance, 34);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SwordofEndlessRestlessLivingEntityIsHitWithTool!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			System.err.println("Failed to load dependency itemstack for procedure SwordofEndlessRestlessLivingEntityIsHitWithTool!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(SwordofEndlessRestlessItem.block, (int) (1)).getItem()) == (true))) {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 60, (int) 1, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 60, (int) 1, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, (int) 60, (int) 1, (false), (false)));
			((itemstack)).addEnchantment(Enchantments.SHARPNESS, (int) 1);
		} else {
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SPEED, (int) 0, (int) 0, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, (int) 0, (int) 0, (false), (false)));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 0, (int) 0, (false), (false)));
		}
	}
}
