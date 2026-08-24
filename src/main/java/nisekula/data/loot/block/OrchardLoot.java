package nisekula.data.loot.block;

import nisekula.world.item.FoodItem;
import nisekula.world.loot.LootSystem;

import java.util.Arrays;
import java.util.Collections;

public class OrchardLoot {

    // 葉っぱブロックのID定義
    public static final int APPLE_LEAVES    = 9;
    public static final int ZELKOVA_LEAVES  = 10;
    public static final int REDWOOD_LEAVES  = 11;
    public static final int MANDARIN_LEAVES = 12;

    /**
     * 果樹園エリアの葉っぱブロックのドロップテーブルを初期化・登録する
     */
    public static void init() {
        // リンゴの木の葉っぱ (破壊時に一定確率でリンゴをドロップ)
        LootSystem.registerBlockLoot(APPLE_LEAVES, Arrays.asList(
            new LootSystem.LootEntry(FoodItem.APPLE, 0.15f, 1, 1) // 15%の確率で1個
        ));

        // ミカンの木の葉っぱ (破壊時に一定確率でミカンをドロップ)
        LootSystem.registerBlockLoot(MANDARIN_LEAVES, Arrays.asList(
            new LootSystem.LootEntry(FoodItem.ORANGE, 0.20f, 1, 1) // 20%の確率で1個
        ));

        // ケヤキやレッドウッドの葉っぱ（今回は通常の木材用またはドロップなし等、空または別ドロップを設定）
        LootSystem.registerBlockLoot(ZELKOVA_LEAVES, Collections.emptyList());
        LootSystem.registerBlockLoot(REDWOOD_LEAVES, Collections.emptyList());
    }
}
