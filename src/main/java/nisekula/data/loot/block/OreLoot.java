package nisekula.world.loot;

import nisekula.world.block.Blocks;
import nisekula.world.item.ItemRegistry;

import java.util.Arrays;
import java.util.Collections;

public class OreLoot {

    // --- 鉱石・主要ブロックのID定義例 ---
    public static final int STONE_ORE          = 20;
    public static final int IRON_ORE           = 21;
    public static final int YELLOW_CRYSTAL_ORE = 22;
    public static final int DIAMOND_ORE        = 23;
    public static final int LUXALBSTONE        = 24; // 白光岩

    /**
     * 各種鉱石ブロックのドロップテーブルを初期化・登録する
     */
    public static void init() {
        // 石鉱石 (破壊時に石の素材やブロック自体をドロップ)
        LootSystem.registerBlockLoot(STONE_ORE, Arrays.asList(
            new LootSystem.LootEntry(STONE_ORE, 1.0f, 1, 1)
        ));

        // 鉄鉱石
        LootSystem.registerBlockLoot(IRON_ORE, Arrays.asList(
            new LootSystem.LootEntry(IRON_ORE, 1.0f, 1, 1)
        ));

        // イエロークリスタル鉱石
        LootSystem.registerBlockLoot(YELLOW_CRYSTAL_ORE, Arrays.asList(
            new LootSystem.LootEntry(YELLOW_CRYSTAL_ORE, 1.0f, 1, 1)
        ));

        // ダイヤ鉱石
        LootSystem.registerBlockLoot(DIAMOND_ORE, Arrays.asList(
            new LootSystem.LootEntry(DIAMOND_ORE, 1.0f, 1, 1)
        ));

        // 白光岩 (Luxalbstone) - 洞窟の要、ムカデも好むブロック
        LootSystem.registerBlockLoot(LUXALBSTONE, Arrays.asList(
            new LootSystem.LootEntry(LUXALBSTONE, 1.0f, 1, 1)
        ));
    }
}
