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
package net.mcreator.cascadesorcery;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@CascadeSorceryModElements.ModElement.Tag
public class Names extends CascadeSorceryModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public Names(CascadeSorceryModElements instance) {
		super(instance, 37);
	}
	
	public static final String Client_CONFIG = "cs-client.toml";

	public static final String Server_CONFIG = "cs-server.toml";
}
