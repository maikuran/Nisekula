package nisekula.world.item;

import nisekula.lib.regi.ItemRegistry;
import static nisekula.lib.regi.ItemRegistry.r;
import static nisekula.lib.regi.ItemRegistry.Tag.*;

public class SwordItem {

    // --- 剣のアイテムID定義 ---
    public static final int WOODEN_SWORD = 101;
    public static final int STONE_SWORD  = 102;
    public static final int IRON_SWORD   = 103;
    public static final int YELLOW_CRYSTAL_SWORD = 104;
    public static final int DIAMOND_SWORD = 105;

    // 剣データの拡張構造
    public static class SwordProperties extends ItemRegistry.Properties {
        public float attackSpeed; // 攻撃速度 (Java版表記: 1秒あたりの振り回し可能回数)

        public SwordProperties(float damage, float speed, int tier) {
            dmg(damage);
            this.attackSpeed = speed;
            tier(tier);
            tag(SWORD); // SWORDタグを自動付与
        }
    }

    /**
     * 5種類の剣を一括登録する処理
     */
    public static void init() {
        // 木の剣: 攻撃力 4.0, 攻撃速度 1.91
        r(WOODEN_SWORD, "wooden_sword", 
            new SwordProperties(4.0f, 1.91f, 1));

        // 石の剣: 攻撃力 5.0, 攻撃速度 1.84
        r(STONE_SWORD, "stone_sword", 
            new SwordProperties(5.0f, 1.84f, 2));

        // 鉄の剣: 攻撃力 6.0, 攻撃速度 1.77
        r(IRON_SWORD, "iron_sword", 
            new SwordProperties(6.0f, 1.77f, 3));

        // イエロークリスタルの剣: 攻撃力 7.0, 攻撃速度 1.70
        r(YELLOW_CRYSTAL_SWORD, "yellow_crystal_sword", 
            new SwordProperties(7.0f, 1.70f, 4));

        // ダイヤの剣: 攻撃力 8.0, 攻撃速度 1.63
        r(DIAMOND_SWORD, "diamond_sword", 
            new SwordProperties(8.0f, 1.63f, 5));
    }

    /**
     * 攻撃クールダウン（フレーム数）の計算ヘルパー
     * @param attackSpeed 攻撃速度 (例: 1.91)
     * @param targetFps ゲームの目標FPS (例: 60)
     * @return 次の攻撃までに必要なフレーム数
     */
    public static int getCooldownFrames(float attackSpeed, int targetFps) {
        if (attackSpeed <= 0) return targetFps;
        return Math.max(1, (int) (targetFps / attackSpeed));
    }
}
