package nisekula.world.gene.sys;

import nisekula.lib.func.MathLib;
import nisekula.world.WorldData;
import nisekula.world.block.Blocks;

public class PlainBiome {

    public static final int BIOME_ID = 10;

    /**
     * 平原バイオームの初期化とレジストリ登録
     */
    public static void init() {
        WorldgenSystems.registerBiome(BIOME_ID, "plain", 0.7f, 0.8f);
    }

    /**
     * 指定されたX座標列の地形を WorldData に書き込む
     * @param worldData 書き込み先のワールドデータ
     * @param x ワールドX座標
     * @param maxWorldHeight ワールドの最大高さ
     */
    public static void generateColumn(WorldData worldData, int x, int maxWorldHeight) {
        // 1. パーリンノイズやサイン波（MathLib.simpleWave）を使って地表の高さを計算
        int seaLevel = 64;
        int surfaceHeight = seaLevel + (int)(MathLib.simpleWave(x * 0.05f, 1.0f, 10.0f) + MathLib.simpleWave(x * 0.01f, 1.0f, 25.0f));

        // 2. 地下から上空までループして WorldData にブロックを配置
        for (int y = 0; y <= maxWorldHeight; y++) {
            if (y == surfaceHeight) {
                // 地表は土 (DIRT)
                worldData.setBlock(x, y, Blocks.DIRT);
            } else if (y < surfaceHeight) {
                // 地中・下層
                if (y < surfaceHeight - 15) {
                    worldData.setBlock(x, y, Blocks.DEEPSLATE); // 深層は深層石
                } else {
                    worldData.setBlock(x, y, Blocks.STONE);    // その上は通常の石
                }
            } else {
                // 地表より上は空気 (AIR)
                worldData.setBlock(x, y, Blocks.AIR);
            }
        }
    }
}
