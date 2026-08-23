package nisekula.world;

import java.util.HashMap;
import java.util.Map;

public class EntityData {

    // --- 基本情報 ---
    public int entityTypeId;        // どのモブ/プレイヤーか (EntityRegistryのID)
    public long entityId;          // 個体識別用のユニークID

    // --- 位置・物理 ---
    public float x, y;             // ワールド座標
    public float vx, vy;           // 速度ベクトル
    public boolean isGrounded;     // 着地しているか

    // --- ステータス・フラグ ---
    public float currentHp;        // 現在のHP
    public boolean isDead;         // 死亡フラグ
    public boolean isControllable; // プレイヤー操作可能か (プレイヤー自身や乗れるモブなど)
    public boolean isTamed;        // 手懐けられているか
    public String ownerUUID;       // 手懐けたプレイヤーの識別子 (未手懐けならnull)

    // --- ステータスエフェクト (効果名 -> 残りフレーム数 or 残り秒数) ---
    public Map<String, Integer> activeEffects = new HashMap<>();

    public EntityData(long entityId, int entityTypeId, float x, float y, float maxHp) {
        this.entityId = entityId;
        this.entityTypeId = entityTypeId;
        this.x = x;
        this.y = y;
        this.currentHp = maxHp;
        this.isDead = false;
        this.isControllable = false;
        this.isTamed = false;
        this.ownerUUID = null;
    }

    // --- 操作・状態制御メソッド ---

    /** ダメージ処理 */
    public void damage(float amount) {
        if (isDead) return;
        this.currentHp -= amount;
        if (this.currentHp <= 0) {
            this.currentHp = 0;
            this.isDead = true;
        }
    }

    /** 回復処理 */
    public void heal(float amount, float maxHp) {
        if (isDead) return;
        this.currentHp = Math.min(this.currentHp + amount, maxHp);
    }

    /** 手懐ける */
    public void tame(String playerUUID) {
        this.isTamed = true;
        this.ownerUUID = playerUUID;
    }

    /** エフェクトの付与 */
    public void addEffect(String effectName, int duration) {
        activeEffects.put(effectName, duration);
    }

    /** エフェクトの削除 */
    public void removeEffect(String effectName) {
        activeEffects.remove(effectName);
    }

    /** 特定のエフェクトがかかっているか確認 */
    public boolean hasEffect(String effectName) {
        return activeEffects.containsKey(effectName) && activeEffects.get(effectName) > 0;
    }

    /** 毎フレーム呼び出す更新処理 (エフェクトの残り時間減少など) */
    public void tick() {
        if (isDead) return;

        // エフェクトのタイマー更新
        if (!activeEffects.isEmpty()) {
            activeEffects.entrySet().removeIf(entry -> {
                int timeLeft = entry.getValue() - 1;
                if (timeLeft <= 0) return true; // タイマー終了で削除
                entry.setValue(timeLeft);
                return false;
            });
        }
    }
}
