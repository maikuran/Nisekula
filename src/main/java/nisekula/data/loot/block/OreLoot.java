package nisekula.world.block;

import nisekula.world.loot.LootSystem;
import java.util.Arrays;

public class OreLoot {

    /**
     * 鉱石類および白光岩のドロップテーブルを初期化・登録する
     */
    public static void init() {
        // 鉄鉱石 (破壊時に鉄鉱石ブロック自身、または将来の鉱石アイテムをドロップ)
        LootSystem.registerBlockLoot(Blocks.IRON_ORE, Arrays.asList(
            new LootSystem.LootEntry(Blocks.IRON_ORE, 1.0f, 1, 1)
        ));

        // イエロークリスタル鉱石
        LootSystem.registerBlockLoot(Blocks.YELLOW_CRYSTAL_ORE, Arrays.asList(
            new LootSystem.LootEntry(Blocks.YELLOW_CRYSTAL_ORE, 1.0f, 1, 1)
        ));

        // ダイヤモンド鉱石
        LootSystem.registerBlockLoot(Blocks.DIAMOND_ORE, Arrays.asList(
            new LootSystem.LootEntry(Blocks.DIAMOND_ORE, 1.0f, 1, 1)
        ));

        // 白光岩 (Luxalbstone)
        LootSystem.registerBlockLoot(Blocks.LUXALBSTONE, Arrays.asList(
            new LootSystem.LootEntry(Blocks.LUXALBSTONE, 1.0f, 1, 1)
        ));
    }
}
