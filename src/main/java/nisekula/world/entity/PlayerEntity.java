package nisekula.world.entity;

import nisekula.lib.regi.EntityRegistry;
import nisekula.world.EntityData;

import static nisekula.lib.regi.EntityRegistry.r;

public class PlayerEntity extends EntityData {

    public static final int PLAYER_ENTITY_ID = 0;
    public static final float DEFAULT_MAX_HP = 20.0f;
    public static final float DEFAULT_ATTACK_DAMAGE = 2.0f;

    // --- プレイヤー操作実行真偽値 (入力フラグ) ---
    public boolean inputUse = false;        // ブロック設置 / 右クリック使用
    public boolean inputBreak = false;      // ブロック破壊 / 左クリック攻撃
    public boolean inputRun = false;        // 走る (ダッシュ)
    public boolean inputSneak = false;      // スニーク (しゃがみ)
    public boolean inputDebug = false;      // デバッグ情報表示 (F3)
    public boolean inputRDToggle = false;   // 描画距離変更 (Render Distance Toggle)
    public boolean inputInventory = false;  // インベントリ開閉
    public boolean inputMode = false;       // ゲームモード切り替え
    public boolean inputSettings = false;   // 設定メニュー
    public boolean inputQuit = false;       // ゲーム終了 / ポーズ

    public PlayerEntity(long entityId, float x, float y) {
        super(entityId, PLAYER_ENTITY_ID, x, y, DEFAULT_MAX_HP);
        this.isControllable = true; // プレイヤーは操作可能
    }

    /**
     * EntityRegistry にプレイヤー（ID: 0）を登録する
     */
    public static void register() {
        r(PLAYER_ENTITY_ID, "player",
            new EntityRegistry.Properties()
                .hp(DEFAULT_MAX_HP)
                .spd(1.5f)
                .size(0.6f, 1.8f)
                .dmg(DEFAULT_ATTACK_DAMAGE),
            new EntityRegistry.Goals() // プレイヤーはAI (Goals) の代わりにプレイヤー操作を使用
        );
    }

    /**
     * 全ての入力フラグを一括リセットする処理
     */
    public void resetInputs() {
        this.inputUse = false;
        this.inputBreak = false;
        this.inputRun = false;
        this.inputSneak = false;
        this.inputDebug = false;
        this.inputRDToggle = false;
        this.inputInventory = false;
        this.inputMode = false;
        this.inputSettings = false;
        this.inputQuit = false;
    }
}
