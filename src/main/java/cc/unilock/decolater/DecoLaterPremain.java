package cc.unilock.decolater;

import cc.unilock.decolater.transformer.BiomeDecoratorTransformer;
import cc.unilock.decolater.transformer.ClassReaderTransformer;
import cc.unilock.decolater.transformer.WorldServerTransformer;
import nilloader.api.ClassTransformer;
import nilloader.api.ModRemapper;
import nilloader.api.NilLogger;

public class DecoLaterPremain implements Runnable {
	public static final NilLogger LOGGER = NilLogger.get("DecorateLater");

	@Override
	public void run() {
		ModRemapper.setTargetMapping("default");

		// Required for Forge compatibility
		ClassTransformer.register(new ClassReaderTransformer());

		// Fix it!
		ClassTransformer.register(new BiomeDecoratorTransformer());
		ClassTransformer.register(new WorldServerTransformer());
	}
}
