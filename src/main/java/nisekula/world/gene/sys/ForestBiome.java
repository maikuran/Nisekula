package nisekula.world.gene.sys;

import nisekula.lib.func.MathLib;
import nisekula.lib.regi.FeatureRegistry;
import nisekula.world.WorldData;
import nisekula.world.block.Blocks;

public class ForestBiome {

    // --- バイオームIDの定義 ---
    public static final int ORCHARD_FOREST_ID = 20;
    public static final int REDWOOD_FOREST_ID = 21;

    /**
     * 森系のバイオームを一括初期化・登録する
     */
    public static void init() {
        // OrchardForest (果樹園の森): 地表は土、下層は石
        WorldgenSystems.registerBiome(ORCHARD_FOREST_ID, "orchard_forest", 13, 16, 1);

        // RedwoodForest (レッドウッドの森): 地表は土、下層は石
        WorldgenSystems.registerBiome(REDWOOD_FOREST_ID, "redwood_forest", 13, 16, 1);
    }

    /**
     * OrchardForest の地形生成と木（リンゴ・ミカン）の配置ジェネレーター
     */
    public static void generateOrchardForestColumn(WorldData worldData, int x, int maxWorldHeight) {
        int seaLevel = 64;
        int surfaceHeight = seaLevel + (int)(MathLib.simpleWave(x * 0.04f, 1.0f, 8.0f));

        // 1. 地形の埋め込み
        for (int y = 0; y <= maxWorldHeight; y++) {
            if (y == surfaceHeight) {
                worldData.setBlock(x, y, Blocks.DIRT);
            } else if (y < surfaceHeight) {
                if (y < surfaceHeight - 15) {
                    worldData.setBlock(x, y, Blocks.DEEPSLATE);
                } else {
                    worldData.setBlock(x, y, Blocks.STONE);
                }
            } else {
                worldData.setBlock(x, y, Blocks.AIR);
            }
        }

        // 2. 小型木（リンゴの木 or ミカンの木）の生成判定
        if (Math.random() < 0.06f) {
            String treeName = Math.random() < 0.5f ? "apple_tree" : "mandarin_tree";
            FeatureRegistry.Feature feature = FeatureRegistry.g(treeName);
            if (feature != null && feature.generator != null) {
                feature.generator.generate(worldData, x, surfaceHeight + 1);
            }
        }
    }

    /**
     * RedwoodForest の地形生成と木（レッドウッド等）の配置ジェネレーター
     */
    public static void generateRedwoodForestColumn(WorldData worldData, int x, int maxWorldHeight) {
        int seaLevel = 64;
        // やや起伏を激しくしたレッドウッド向けの高低差
        int surfaceHeight = seaLevel + (int)(MathLib.simpleWave(x * 0.03f, 1.0f, 15.0f) + MathLib.simpleWave(x * 0.08f, 1.0f, 4.0f));

        // 1. 地形の埋め込み
        for (int y = 0; y <= maxWorldHeight; y++) {
            if (y == surfaceHeight) {
                worldData.setBlock(x, y, Blocks.DIRT);
            } else if (y < surfaceHeight) {
                if (y < surfaceHeight - 15) {
                    worldData.setBlock(x, y, Blocks.DEEPSLATE);
                } else {
                    worldData.setBlock(x, y, Blocks.STONE);
                }
            } else {
                worldData.setBlock(x, y, Blocks.AIR);
            }
        }

        // 2. 大型木（レッドウッドの木やケヤキ）の生成判定
        if (Math.random() < 0.04f) {
            String treeName = Math.random() < 0.7f ? "redwood_tree" : "zelkova_tree";
            FeatureRegistry.Feature feature = FeatureRegistry.g(treeName);
            if (feature != null && feature.generator != null) {
                feature.generator.generate(worldData, x, surfaceHeight + 1);
            }
        }
    }
}
