package nisekula.world.ai;

import nisekula.world.EntityData;

public class ProwlGoal {
    public float speedMultiplier;
    public boolean isStealth; // 忍び足モードか

    public ProwlGoal(float speedMultiplier, boolean isStealth) {
        this.speedMultiplier = speedMultiplier;
        this.isStealth = isStealth;
    }

    public void tick(EntityData entity) {
        // ランダムな移動ベクトルの設定や、スニーク状態の切り替え
        if (isStealth) {
            entity.vx *= 0.5f * speedMultiplier; // 忍び足で移動速度低下
        } else {
            entity.vx = (float)(Math.random() - 0.5) * speedMultiplier;
        }
    }
}
