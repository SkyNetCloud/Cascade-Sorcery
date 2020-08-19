package ca.skynetcloud.cascadesorcery.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import ca.skynetcloud.cascadesorcery.item.MoneyCardItem;
import ca.skynetcloud.cascadesorcery.CascadeSorceryModElements;

import java.util.Map;

@CascadeSorceryModElements.ModElement.Tag
public class GiveMoneyCommandExecutedProcedure extends CascadeSorceryModElements.ModElement {
	public GiveMoneyCommandExecutedProcedure(CascadeSorceryModElements instance) {
		super(instance, 39);
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure GiveMoneyCommandExecuted!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double Money = 0;
		if (((entity instanceof PlayerEntity)
				? ((PlayerEntity) entity).inventory.hasItemStack(new ItemStack(MoneyCardItem.block, (int) (1)))
				: false)) {
			Money = (double) 0;
		}
	}
}
