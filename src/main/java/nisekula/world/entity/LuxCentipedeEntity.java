package nisekula.world.entity;

import nisekula.lib.regi.EntityRegistry;
import nisekula.world.EntityData;
import nisekula.world.ai.ProwlGoal;
import nisekula.world.ai.TargetAttackGoal;
import nisekula.world.ai.LookAtPlayerGoal;
import nisekula.world.ai.FavoriteBlockGoal;
import nisekula.world.block.Blocks;

import static nisekula.lib.regi.EntityRegistry.r;

public class LuxCentipedeEntity extends EntityData {

    public static final int ENTITY_ID = 10; // ムカデの固有ID
    public static final float MAX_HP = 30.0f;
    public static final float ATTACK_DAMAGE = 3.5f;
    public static final float MOVE_SPEED = 1.8f; // 素早い移動速度

    // AI ゴールインスタンス
    private final ProwlGoal prowlGoal;
    private final TargetAttackGoal attackGoal;
    private final LookAtPlayerGoal lookGoal;
    private final FavoriteBlockGoal favoriteBlockGoal;

    public LuxCentipedeEntity(long entityId, float x, float y) {
        super(entityId, ENTITY_ID, x, y, MAX_HP);
        
        // AIの初期化
        this.prowlGoal = new ProwlGoal(MOVE_SPEED, true); // 暗闇を忍び寄る
        this.attackGoal = new TargetAttackGoal(1.2f, ATTACK_DAMAGE, 30); // 近接攻撃(30フレーム毎)
        this.lookGoal = new LookAtPlayerGoal(8.0f); // 8ブロック内のプレイヤーを注視
        this.favoriteBlockGoal = new FavoriteBlockGoal(Blocks.LUXALBSTONE, 10); // 白光岩を好む
    }

    /**
     * EntityRegistry に白光ムカデ（LuxCentipede）を登録する
     */
    public static void register() {
        r(ENTITY_ID, "lux_centipede",
            new EntityRegistry.Properties()
                .hp(MAX_HP)
                .spd(MOVE_SPEED)
                .size(0.8f, 0.5f) // 平べったいムカデの判定サイズ
                .hostile(true)    // 敵対的モブ
                .dmg(ATTACK_DAMAGE),
            new EntityRegistry.Goals()
                .add("prowl")
                .add("target_attack")
                .add("look_at_player")
                .add("favorite_luxalbstone")
        );
    }

    /**
     * フレームごとのAI思考およびステータス更新
     */
    public void updateAI(PlayerEntity player) {
        if (isDead) return;

        // エフェクトなどの共通更新
        tick();

        // プレイヤーへのアタック / 視線制御
        if (player != null && !player.isDead) {
            lookGoal.tick(this, player);
            attackGoal.tick(this, player);
        } else {
            // ターゲットがいない場合はうろつき動作
            prowlGoal.tick(this);
        }
    }
}
