package nisekula.world.feature;

import nisekula.lib.regi.FeatureRegistry;
import nisekula.world.block.Blocks;
import nisekula.world.loot.LootSystem;
import java.util.Arrays;

public class WoodFeature {

    /**
     * 木の生成機能と、原木（ログ）のドロップ設定を一括初期化する
     */
    public static void init() {
        // --- 1. 原木(Log)のブロック破壊時ドロップ設定 ---
        // どの原木を壊しても対応する原木アイテム/ブロックが確実にドロップするように登録
        LootSystem.registerBlockLoot(Blocks.APPLE_LOG, Arrays.asList(
            new LootSystem.LootEntry(Blocks.APPLE_LOG, 1.0f, 1, 1)
        ));
        LootSystem.registerBlockLoot(Blocks.ZELKOVA_LOG, Arrays.asList(
            new LootSystem.LootEntry(Blocks.ZELKOVA_LOG, 1.0f, 1, 1)
        ));
        LootSystem.registerBlockLoot(Blocks.REDWOOD_LOG, Arrays.asList(
            new LootSystem.LootEntry(Blocks.REDWOOD_LOG, 1.0f, 1, 1)
        ));
        LootSystem.registerBlockLoot(Blocks.MANDARIN_LOG, Arrays.asList(
            new LootSystem.LootEntry(Blocks.MANDARIN_LOG, 1.0f, 1, 1)
        ));

        // --- 2. FeatureRegistry を使った木の生成ロジック登録 ---

        // 【小型の木】リンゴの木 (コンパクトな高さと葉の広がり)
        FeatureRegistry.registerFeature("apple_tree", (world, x, y, z) -> {
            // 幹 (高さ 3〜4 ブロック)
            for (int dy = 0; dy < 3; dy++) {
                world.setBlock(x, y + dy, z, Blocks.APPLE_LOG);
            }
            // 葉 (上部に小さく配置)
            world.setBlock(x, y + 3, z, Blocks.APPLE_LEAVES);
            world.setBlock(x + 1, y + 2, z, Blocks.APPLE_LEAVES);
            world.setBlock(x - 1, y + 2, z, Blocks.APPLE_LEAVES);
            world.setBlock(x, y + 2, z + 1, Blocks.APPLE_LEAVES);
            world.setBlock(x, y + 2, z - 1, Blocks.APPLE_LEAVES);
        });

        // 【小型の木】ミカンの木 (リンゴ同様にコンパクト)
        FeatureRegistry.registerFeature("mandarin_tree", (world, x, y, z) -> {
            // 幹 (高さ 3 ブロック)
            for (int dy = 0; dy < 3; dy++) {
                world.setBlock(x, y + dy, z, Blocks.MANDARIN_LOG);
            }
            // 葉 (上部に小さく配置)
            world.setBlock(x, y + 3, z, Blocks.MANDARIN_LEAVES);
            world.setBlock(x + 1, y + 2, z, Blocks.MANDARIN_LEAVES);
            world.setBlock(x - 1, y + 2, z, Blocks.MANDARIN_LEAVES);
            world.setBlock(x, y + 2, z + 1, Blocks.MANDARIN_LEAVES);
            world.setBlock(x, y + 2, z - 1, Blocks.MANDARIN_LEAVES);
        });

        // 【大型の木】ケヤキの木 (太く高くそびえ立つ)
        FeatureRegistry.registerFeature("zelkova_tree", (world, x, y, z) -> {
            // 幹 (高さ 6 ブロック、太さ2x2を想定した中心柱)
            for (int dy = 0; dy < 6; dy++) {
                world.setBlock(x, y + dy, z, Blocks.ZELKOVA_LOG);
                world.setBlock(x + 1, y + dy, z, Blocks.ZELKOVA_LOG);
            }
            // 葉 (ダイナミックに広く展開)
            for (int dx = -2; dx <= 3; dx++) {
                for (int dz = -2; dz <= 2; dz++) {
                    world.setBlock(x + dx, y + 5, z + dz, Blocks.ZELKOVA_LEAVES);
                    world.setBlock(x + dx, y + 6, z + dz, Blocks.ZELKOVA_LEAVES);
                }
            }
        });

        // 【大型の木】レッドウッドの木 (さらに超高層な木)
        FeatureRegistry.registerFeature("redwood_tree", (world, x, y, z) -> {
            // 幹 (高さ 8 ブロック)
            for (int dy = 0; dy < 8; dy++) {
                world.setBlock(x, y + dy, z, Blocks.REDWOOD_LOG);
            }
            // 葉 (円錐状に何層にもわたって配置)
            for (int h = 5; h <= 8; h++) {
                world.setBlock(x + 1, y + h, z, Blocks.REDWOOD_LEAVES);
                world.setBlock(x - 1, y + h, z, Blocks.REDWOOD_LEAVES);
                world.setBlock(x, y + h, z + 1, Blocks.REDWOOD_LEAVES);
                world.setBlock(x, y + h, z - 1, Blocks.REDWOOD_LEAVES);
            }
            world.setBlock(x, y + 9, z, Blocks.REDWOOD_LEAVES); // 頂点
        });
    }
}
