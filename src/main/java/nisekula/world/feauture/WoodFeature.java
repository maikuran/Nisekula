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

        // --- 2. FeatureRegistry を使った木の生成ロジック登録 (2D平面用) ---

        // 【小型の木】リンゴの木
        FeatureRegistry.r(101, "apple_tree",
            new FeatureRegistry.Properties().ch(0.05f).height(60, 120).size(4),
            (world, x, y) -> {
                // 幹 (高さ 3 ブロック)
                for (int dy = 0; dy < 3; dy++) {
                    world.setBlock(x, y + dy, Blocks.APPLE_LOG);
                }
                // 葉 (2D横スクロールや見下ろしを想定した左右・上部の配置)
                world.setBlock(x, y + 3, Blocks.APPLE_LEAVES);
                world.setBlock(x + 1, y + 2, Blocks.APPLE_LEAVES);
                world.setBlock(x - 1, y + 2, Blocks.APPLE_LEAVES);
            }
        );

        // 【小型の木】ミカンの木
        FeatureRegistry.r(102, "mandarin_tree",
            new FeatureRegistry.Properties().ch(0.05f).height(60, 120).size(4),
            (world, x, y) -> {
                // 幹 (高さ 3 ブロック)
                for (int dy = 0; dy < 3; dy++) {
                    world.setBlock(x, y + dy, Blocks.MANDARIN_LOG);
                }
                // 葉
                world.setBlock(x, y + 3, Blocks.MANDARIN_LEAVES);
                world.setBlock(x + 1, y + 2, Blocks.MANDARIN_LEAVES);
                world.setBlock(x - 1, y + 2, Blocks.MANDARIN_LEAVES);
            }
        );

        // 【大型の木】ケヤキの木
        FeatureRegistry.r(103, "zelkova_tree",
            new FeatureRegistry.Properties().ch(0.02f).height(60, 130).size(8),
            (world, x, y) -> {
                // 幹 (高さ 6 ブロック、2Dなので太さは横への広がりとして表現)
                for (int dy = 0; dy < 6; dy++) {
                    world.setBlock(x, y + dy, Blocks.ZELKOVA_LOG);
                    world.setBlock(x + 1, y + dy, Blocks.ZELKOVA_LOG);
                }
                // 葉 (上部に広く展開)
                for (int dx = -2; dx <= 3; dx++) {
                    world.setBlock(x + dx, y + 5, Blocks.ZELKOVA_LEAVES);
                    world.setBlock(x + dx, y + 6, Blocks.ZELKOVA_LEAVES);
                }
            }
        );

        // 【大型の木】レッドウッドの木
        FeatureRegistry.r(104, "redwood_tree",
            new FeatureRegistry.Properties().ch(0.02f).height(60, 140).size(10),
            (world, x, y) -> {
                // 幹 (高さ 8 ブロック)
                for (int dy = 0; dy < 8; dy++) {
                    world.setBlock(x, y + dy, Blocks.REDWOOD_LOG);
                }
                // 葉 (左右対称に配置)
                for (int h = 5; h <= 8; h++) {
                    world.setBlock(x + 1, y + h, Blocks.REDWOOD_LEAVES);
                    world.setBlock(x - 1, y + h, Blocks.REDWOOD_LEAVES);
                }
                world.setBlock(x, y + 9, Blocks.REDWOOD_LEAVES); // 頂点
            }
        );
    }
}
