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
 * outside net.mcreator.cascadesorcery as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package ca.skynetcloud.cascadesorcery;

import java.io.File;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import ca.skynetcloud.cascadesorcery.CSConfigInit;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.ModLoadingContext;

import ca.skynetcloud.cascadesorcery.Names;

@CascadeSorceryModElements.ModElement.Tag
public class ConfigLoader extends CascadeSorceryModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public ConfigLoader(CascadeSorceryModElements instance) {
		super(instance, 35);
	}

	@Override
	public void initElements() {

	ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigLoader.CLIENT, Names.Client_CONFIG);
	ConfigLoader.loadConfig(ConfigLoader.CLIENT, FMLPaths.CONFIGDIR.get().resolve(Names.Client_CONFIG).toString());

	ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, ConfigLoader.COMMON, Names.Server_CONFIG);
	ConfigLoader.loadConfig(ConfigLoader.COMMON, FMLPaths.CONFIGDIR.get().resolve(Names.Server_CONFIG).toString());
	}

	@Override
	public void init(FMLCommonSetupEvent event) {

	}

	@Override
	public void serverLoad(FMLServerStartingEvent event) {

	}

	private static final ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec COMMON;

	private static final ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec CLIENT;

	static {
		CSConfigInit.init(COMMON_BUILDER, CLIENT_BUILDER);

		COMMON = COMMON_BUILDER.build();
		CLIENT = CLIENT_BUILDER.build();
	}

	public static void loadConfig(ForgeConfigSpec config, String path) {
		final CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave()
				.writingMode(WritingMode.REPLACE).build();
		file.load();
		config.setConfig(file);
	}
	
}
