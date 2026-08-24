package nisekula.world.item;

import nisekula.lib.regi.ItemRegistry;
import static nisekula.lib.regi.ItemRegistry.r;
import static nisekula.lib.regi.ItemRegistry.Tag.*;

public class SpearItem {

    // --- 槍のアイテムID定義 ---
    public static final int WOODEN_SPEAR = 201;
    public static final int STONE_SPEAR  = 202;
    public static final int IRON_SPEAR   = 203;
    public static final int YELLOW_CRYSTAL_SPEAR = 204;
    public static final int DIAMOND_SPEAR = 205;

    // 槍データの拡張構造
    public static class SpearProperties extends ItemRegistry.Properties {
        public float attackSpeed; // 攻撃速度 (1秒あたりの回数)
        public float penetration; // 防具貫通率 (0.2f = 20%)

        public SpearProperties(float damage, float speed, float penetration, int tier) {
            dmg(damage);
            this.attackSpeed = speed;
            this.penetration = penetration;
            tier(tier);
            tag(SPEAR, OFFHANDITEM); // 槍タグと副手装備可能タグを自動付与
        }
    }

    /**
     * 5種類の槍を一括登録する処理
     */
    public static void init() {
        final float PENETRATION_RATE = 0.20f; // 防具貫通 20%

        // 木の槍: 攻撃力 5.5, 攻撃速度 1.40
        r(WOODEN_SPEAR, "wooden_spear",
            new SpearProperties(5.5f, 1.40f, PENETRATION_RATE, 1));

        // 石の槍: 攻撃力 6.5, 攻撃速度 1.30
        r(STONE_SPEAR, "stone_spear",
            new SpearProperties(6.5f, 1.30f, PENETRATION_RATE, 2));

        // 鉄の槍: 攻撃力 8.5, 攻撃速度 1.20
        r(IRON_SPEAR, "iron_spear",
            new SpearProperties(8.5f, 1.20f, PENETRATION_RATE, 3));

        // イエロークリスタルの槍: 攻撃力 9.5, 攻撃速度 1.10
        r(YELLOW_CRYSTAL_SPEAR, "yellow_crystal_spear",
            new SpearProperties(9.5f, 1.10f, PENETRATION_RATE, 4));

        // ダイヤの槍: 攻撃力 12.5, 攻撃速度 1.00
        r(DIAMOND_SPEAR, "diamond_spear",
            new SpearProperties(12.5f, 1.00f, PENETRATION_RATE, 5));
    }

    /**
     * 貫通補正を考慮した最終ダメージ計算処理
     * @param rawDamage 元の攻撃力
     * @param targetArmor Defense値（相手の防具による軽減率など）
     * @param penetration 貫通率 (0.20f)
     */
    public static float calculatePenetratedDamage(float rawDamage, float targetArmor, float penetration) {
        // 相手の防具性能を (1 - 貫通率) 分だけ減算して適用する
        float effectiveArmor = targetArmor * (1.0f - penetration);
        float finalDamage = rawDamage - effectiveArmor;
        return Math.max(1.0f, finalDamage); // 最低でも 1 ダメージは保証
    }
}
