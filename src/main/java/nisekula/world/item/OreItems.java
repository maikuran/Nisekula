package nisekula.world.item;

import nisekula.lib.regi.ItemRegistry;
import static nisekula.lib.regi.ItemRegistry.r;
import static nisekula.lib.regi.ItemRegistry.Tag.*;

public class OreItems {

    // --- 鉱石系アイテムのID定義 (ブロックIDと綺麗に紐付け) ---
    public static final int IRON_ORE_ITEM           = 501;
    public static final int YELLOW_CRYSTAL_ORE_ITEM = 502;
    public static final int DIAMOND_ORE_ITEM        = 503;
    public static final int LUXALBSTONE_ITEM        = 504;

    /**
     * 鉱石ブロックに対応するアイテムを一括登録する処理
     */
    public static void init() {
        // 鉄鉱石アイテム (Blocksタグを付与してブロック設置やクラフトに利用可能にする)
        r(IRON_ORE_ITEM, "iron_ore_item", 
            new ItemRegistry.Properties().tag(BLOCKS, NATURE).tier(3));

        // イエロークリスタル鉱石アイテム
        r(YELLOW_CRYSTAL_ORE_ITEM, "yellow_crystal_ore_item", 
            new ItemRegistry.Properties().tag(BLOCKS, NATURE, ELECTROS).tier(4));

        // ダイヤモンド鉱石アイテム
        r(DIAMOND_ORE_ITEM, "diamond_ore_item", 
            new ItemRegistry.Properties().tag(BLOCKS, NATURE).tier(5));

        // 白光岩アイテム (Luxalbstone)
        r(LUXALBSTONE_ITEM, "luxalbstone_item", 
            new ItemRegistry.Properties().tag(BLOCKS, NATURE, ELECTROS).tier(4));
    }
}
