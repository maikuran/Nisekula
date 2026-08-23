package nisekula.world.ai;

import nisekula.world.EntityData;

public class TargetAttackGoal {
    public float attackRange;
    public float attackDamage;
    public int attackCooldownFrames;
    private int currentCooldown = 0;

    public TargetAttackGoal(float attackRange, float attackDamage, int cooldownFrames) {
        this.attackRange = attackRange;
        this.attackDamage = attackDamage;
        this.attackCooldownFrames = cooldownFrames;
    }

    public void tick(EntityData attacker, EntityData target) {
        if (target == null || target.isDead) return;

        // 距離判定 (2D平面)
        float dx = target.x - attacker.x;
        float dy = target.y - attacker.y;
        float distSq = dx * dx + dy * dy;

        if (distSq <= attackRange * attackRange) {
            // 攻撃クールダウン処理
            if (currentCooldown <= 0) {
                target.damage(attackDamage);
                currentCooldown = attackCooldownFrames;
            }
        } else {
            // ターゲットへ向かって移動
            attacker.vx = Math.signum(dx) * 0.8f;
        }

        if (currentCooldown > 0) currentCooldown--;
    }
}
