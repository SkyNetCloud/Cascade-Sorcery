/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class CascadeSorceryModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside ca.skynetcloud.cascadesorcery as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package ca.skynetcloud.cascadesorcery;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import ca.skynetcloud.cascadesorcery.block.MergerTableBlockBlock;

import ca.skynetcloud.cascadesorcery.block.Shadow_IngotOreBlockBlock;

import ca.skynetcloud.cascadesorcery.item.SwordofEndlessRestlessItem;

import ca.skynetcloud.cascadesorcery.block.Mark1MergerBlock;

import java.util.ArrayList;
import java.util.List;

@mezz.jei.api.JeiPlugin
public class Jei implements IModPlugin {
	public static IJeiHelpers jeiHelper;
	@Override
	public ResourceLocation getPluginUid() {
		return new ResourceLocation("cascade_sorcery", "default");
	}

	@Override
	public void registerCategories(IRecipeCategoryRegistration registration) {
		jeiHelper = registration.getJeiHelpers();
		registration.addRecipeCategories(new MergerTableBlockBlockJeiCategory(jeiHelper.getGuiHelper()));
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		registration.addRecipes(generateMergerTableBlockBlockRecipes(), MergerTableBlockBlockJeiCategory.Uid);
		
	}

	private List<MergerTableBlockBlockJeiCategory.MergerTableBlockBlockRecipeWrapper> generateMergerTableBlockBlockRecipes() {
			
		List<MergerTableBlockBlockJeiCategory.MergerTableBlockBlockRecipeWrapper> recipes = new ArrayList<>();
		
		ArrayList<ItemStack> inputs = new ArrayList<>();
        ArrayList<ItemStack> outputs = new ArrayList<>();
        
        inputs.add(new ItemStack(Shadow_IngotOreBlockBlock.block));
        inputs.add(new ItemStack(Items.GOLDEN_SWORD));
        inputs.add(new ItemStack(Items.IRON_SWORD));
        inputs.add(new ItemStack(Items.DIAMOND_SWORD));
		inputs.add(new ItemStack(Items.NETHER_STAR, 4));
		
        outputs.add(new ItemStack(SwordofEndlessRestlessItem.block));
        outputs.add(new ItemStack(Items.BARRIER));
        outputs.add(new ItemStack(Items.BARRIER));
        outputs.add(new ItemStack(Items.BARRIER));
        

         
		recipes.add(new MergerTableBlockBlockJeiCategory.MergerTableBlockBlockRecipeWrapper(inputs, outputs));
		return recipes;
	}



	@Override
	public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
		registration.addRecipeCatalyst(new ItemStack(MergerTableBlockBlock.block), MergerTableBlockBlockJeiCategory.Uid);
	}

	public static class MergerTableBlockBlockJeiCategory implements IRecipeCategory<MergerTableBlockBlockJeiCategory.MergerTableBlockBlockRecipeWrapper> {
		
		private static ResourceLocation Uid = new ResourceLocation("cascade_sorcery", "mergermark2category");
		
		private static final int input1 = 1;
		private static final int input2 = 2;
		private static final int input3 = 3;
		private static final int input4 = 4;
		private static final int input5 = 5;
		
		private static final int output1 = 6;
		private static final int output2 = 7;
		private static final int output3 = 8;
		private static final int output4 = 9;


		private final String title;
		private final IDrawable background;
		
		public MergerTableBlockBlockJeiCategory(IGuiHelper guiHelper) {
			this.title = "Merger Mark 2";
			this.background = guiHelper.createDrawable(new ResourceLocation("cascade_sorcery", "textures/marger_jei.png"), 0, 0, 176, 166);
		}

		@Override
		public ResourceLocation getUid() {
			return Uid;
		}

		@Override
		public Class<? extends MergerTableBlockBlockRecipeWrapper> getRecipeClass() {
			return MergerTableBlockBlockJeiCategory.MergerTableBlockBlockRecipeWrapper.class;
		}

		@Override
		public String getTitle() {
			return title;
		}

	    @Override
        public IDrawable getBackground() {
            return background;
        }

		@Override
		public IDrawable getIcon() {
			return null;
		}

		@Override
		public void setIngredients(MergerTableBlockBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
            iIngredients.setInputs(VanillaTypes.ITEM, recipeWrapper.getInput());
            iIngredients.setOutputs(VanillaTypes.ITEM, recipeWrapper.getOutput());
		}

		@Override
		public void setRecipe(IRecipeLayout iRecipeLayout, MergerTableBlockBlockRecipeWrapper recipeWrapper, IIngredients iIngredients) {
			IGuiItemStackGroup stacks = iRecipeLayout.getItemStacks();
			
 
			stacks.init(input1, true, 61, 11);
			stacks.set(input1, iIngredients.getInputs(VanillaTypes.ITEM).get(1));
			stacks.init(input2, true, 88, 11);
			stacks.set(input2, iIngredients.getInputs(VanillaTypes.ITEM).get(2));
			stacks.init(input3, true, 115, 11);
			stacks.set(input3, iIngredients.getInputs(VanillaTypes.ITEM).get(3));
			stacks.init(input4, true, 142, 11);
			stacks.set(input4, iIngredients.getInputs(VanillaTypes.ITEM).get(4));
			stacks.init(input5, true, 25, 29);
			stacks.set(input5, iIngredients.getInputs(VanillaTypes.ITEM).get(5));
			
			stacks.init(output1, false, 61, 47);
			stacks.set(output1, iIngredients.getOutputs(VanillaTypes.ITEM).get(6));
			stacks.init(output2, false, 88, 47);
			stacks.set(output2, iIngredients.getOutputs(VanillaTypes.ITEM).get(7));
			stacks.init(output3, false, 115, 47);
			stacks.set(output3, iIngredients.getOutputs(VanillaTypes.ITEM).get(8));
			stacks.init(output4, false, 142, 47);
			stacks.set(output4, iIngredients.getOutputs(VanillaTypes.ITEM).get(9));
		}


		public static class MergerTableBlockBlockRecipeWrapper {
            private ArrayList input;
            private ArrayList output;

            public MergerTableBlockBlockRecipeWrapper(ArrayList input, ArrayList output) {
                this.input = input;
                this.output = output;
            }


            public ArrayList getInput() {
                return input;
            }

            public ArrayList getOutput() {
                return output;
            }
        }
	}
}
