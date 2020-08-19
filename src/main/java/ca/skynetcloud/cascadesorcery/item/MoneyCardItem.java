
package ca.skynetcloud.cascadesorcery.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import ca.skynetcloud.cascadesorcery.procedures.GiveMoneyCommandExecutedProcedure;

import java.util.List;

import net.mcreator.cascadesorcery.itemgroup.CascadeSorceryTabItemGroup;
import net.mcreator.cascadesorcery.CascadeSorceryModElements;

@CascadeSorceryModElements.ModElement.Tag
public class MoneyCardItem extends CascadeSorceryModElements.ModElement {
	
	public static final Item block = null;
	public MoneyCardItem(CascadeSorceryModElements instance) {
		super(instance, 27);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

        


	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(CascadeSorceryTabItemGroup.tab).maxStackSize(1));
			setRegistryName("money_card");
		}
		
	

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public int getUseDuration(ItemStack itemstack) {
			return 0;
		}



	   public static boolean hasCredits(ItemStack stack) {
		CompoundNBT nbt = getNBT(stack);
		double credits = 0;
		if (nbt.contains("credits")) {
			return true;
		}
		return false;
	}

	public static int getCredits(ItemStack stack) {
		if (!stack.isEmpty()) {
			if (stack.getItem() instanceof ItemCustom) {
				if (hasCredits(stack)) {
					return getNBT(stack).getInt("credits");
				}
			}
		}
		return getNBT(stack).getInt("credits");
	}

	public static CompoundNBT getNBT(ItemStack stack) {
		CompoundNBT nbt = stack.getTag();
		if (nbt != null) {
			return nbt;
		}
		return new CompoundNBT();
	}

	public static int addCredits(ItemStack stack, int amount) {
		int newValue = getCredits(stack) + amount;
		setCredits(stack, newValue);
		return newValue;
	}

	public static int shrinkCredits(ItemStack stack, int amount) {
		int newValue = Math.max(getCredits(stack) - amount, 0);
		setCredits(stack, newValue);
		return newValue;
	}

	public static void setCredits(ItemStack stack, int amount) {
		CompoundNBT nbt = getNBT(stack);
		nbt.putInt("credits", amount);
		stack.setTag(nbt);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("CyberCoreDollars: " + getCredits(stack)));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}

		
	}
}
