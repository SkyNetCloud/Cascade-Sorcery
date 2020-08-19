package ca.skynetcloud.cascadesorcery.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import ca.skynetcloud.cascadesorcery.block.Shadow_IngotOreBlockBlock;
import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class ShadowRealmCanTravelThroughPortalProcedure extends CascadeSorceryModElements.ModElement {
	public ShadowRealmCanTravelThroughPortalProcedure(CascadeSorceryModElements instance) {
		super(instance, 19);
	}

	public static boolean executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure ShadowRealmCanTravelThroughPortal!");
			return false;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
				.getItem() == new ItemStack(Shadow_IngotOreBlockBlock.block, (int) (1)).getItem())) {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).inventory
						.clearMatchingItems(p -> new ItemStack(Shadow_IngotOreBlockBlock.block, (int) (1)).getItem() == p.getItem(), (int) 5);
			System.out.println("");
			return (true);
		}
		return (false);
	}
}
