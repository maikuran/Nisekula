package nisekula.world.ai;

import nisekula.world.CoordinateData;
import nisekula.world.EntityData;

public class AvoidBlockGoal {
    public int targetBlockId; // 回避したいブロックID
    public int detectRadius;  // 検知範囲

    public AvoidBlockGoal(int targetBlockId, int detectRadius) {
        this.targetBlockId = targetBlockId;
        this.detectRadius = detectRadius;
    }

    public void tick(EntityData entity, CoordinateData[][] nearbyMap) {
        // 周囲のマップデータをスキャンして該当ブロックがあれば反対方向へ移動速度(vx)をセット
        int ex = (int) entity.x;
        int ey = (int) entity.y;

        for (int dx = -detectRadius; dx <= detectRadius; dx++) {
            for (int dy = -detectRadius; dy <= detectRadius; dy++) {
                // 回避ブロックを検知したら反対方向に逃げる
                // entity.vx = -Math.signum(dx) * 1.2f;
            }
        }
    }
}
