package nisekula.world.item;

import nisekula.lib.regi.ItemRegistry;
import static nisekula.lib.regi.ItemRegistry.r;
import static nisekula.lib.regi.ItemRegistry.Tag.*;

public class ArmorItem {

    // --- 鎧（チェストプレート）のID ---
    public static final int WOODEN_ARMOR          = 301;
    public static final int STONE_ARMOR           = 302;
    public static final int IRON_ARMOR            = 303;
    public static final int YELLOW_CRYSTAL_ARMOR  = 304;
    public static final int DIAMOND_ARMOR         = 305;

    // --- 兜（ヘルメット）のID ---
    public static final int WOODEN_HELMET         = 311;
    public static final int STONE_HELMET          = 312;
    public static final int IRON_HELMET           = 313;
    public static final int YELLOW_CRYSTAL_HELMET = 314;
    public static final int DIAMOND_HELMET        = 315;

    // 防具プロパティ
    public static class ArmorProperties extends ItemRegistry.Properties {
        public float defense; // 防御力数値

        public ArmorProperties(float defense, int tier, boolean isHelmet) {
            this.defense = defense;
            tier(tier);
            if (isHelmet) {
                tag(HELMET, ARMOR);
            } else {
                tag(ARMOR);
            }
        }
    }

    /**
     * 鎧と兜（各5種）を一括登録
     */
    public static void init() {
        // --- 鎧 (Armor / Chestplate) ---
        r(WOODEN_ARMOR,         "wooden_armor",         new ArmorProperties(3.0f,  1, false));
        r(STONE_ARMOR,          "stone_armor",          new ArmorProperties(4.0f,  2, false));
        r(IRON_ARMOR,           "iron_armor",           new ArmorProperties(6.0f,  3, false));
        r(YELLOW_CRYSTAL_ARMOR, "yellow_crystal_armor", new ArmorProperties(9.0f,  4, false));
        r(DIAMOND_ARMOR,        "diamond_armor",        new ArmorProperties(12.0f, 5, false));

        // --- 兜 (Helmet) ---
        r(WOODEN_HELMET,         "wooden_helmet",         new ArmorProperties(2.0f, 1, true));
        r(STONE_HELMET,          "stone_helmet",          new ArmorProperties(3.0f, 2, true));
        r(IRON_HELMET,           "iron_helmet",           new ArmorProperties(4.0f, 3, true));
        r(YELLOW_CRYSTAL_HELMET, "yellow_crystal_helmet", new ArmorProperties(7.0f, 4, true));
        r(DIAMOND_HELMET,        "diamond_helmet",        new ArmorProperties(8.0f, 5, true));
    }

    /**
     * 独自の防御力計算メソッド
     * 
     * 【計算式】
     * 軽減ダメージ = rawDamage * (totalDefense / (totalDefense + 20))
     * 被ダメージ = rawDamage - 軽減ダメージ
     * 
     * 防御力が上がるにつれて軽減率が滑らかに上昇し（防御力20で50%カット）、
     * どれだけ防御力が高くても被ダメージが完全0にはならない（最低1.0は受ける）マイクラとは異なる独自の計算式です。
     *
     * @param rawDamage 元の受けるダメージ
     * @param totalDefense 装備している防御力の合計値
     * @return 最終的にプレイヤー/モブが受けるダメージ
     */
    public static float calculateDamage(float rawDamage, float totalDefense) {
        if (totalDefense <= 0) return rawDamage;

        // 独自の軽減率計算
        float reductionRatio = totalDefense / (totalDefense + 20.0f);
        float finalDamage = rawDamage * (1.0f - reductionRatio);

        // 最低受けダメージ補正 (最低でも1.0ダメージ)
        return Math.max(1.0f, finalDamage);
    }
}
