package nisekula.world.ai;

import nisekula.world.EntityData;
import nisekula.world.entity.PlayerEntity;

public class LookAtPlayerGoal {
    public float lookDistance;
    public boolean isLookingAtPlayer = false;

    public LookAtPlayerGoal(float lookDistance) {
        this.lookDistance = lookDistance;
    }

    public void tick(EntityData entity, PlayerEntity player) {
        if (player == null || player.isDead) {
            isLookingAtPlayer = false;
            return;
        }

        float dx = player.x - entity.x;
        float dy = player.y - entity.y;
        float distSq = dx * dx + dy * dy;

        if (distSq <= lookDistance * lookDistance) {
            isLookingAtPlayer = true;
            // プレイヤーの方向に向きを固定するフラグ調整など
        } else {
            isLookingAtPlayer = false;
        }
    }
}
