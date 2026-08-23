package nisekula.lib.func;

public class MathLib {

    // ==========================================
    // 定数（ショートカット）
    // ==========================================
    public static final float PI = (float) Math.PI;         // 円周率 π
    public static final float PI2 = (float) (Math.PI * 2);  // 2π (360度)
    public static final float PI_2 = (float) (Math.PI / 2); // π/2 (90度)
    public static final float E = (float) Math.E;           // ネイピア数 e
    public static final float DEG2RAD = PI / 180.0f;       // 度 → ラジアン変換
    public static final float RAD2DEG = 180.0f / PI;       // ラジアン → 度変換

    // ==========================================
    // 四則演算・基本操作
    // ==========================================
    public static float add(float a, float b) { return a + b; }
    public static float sub(float a, float b) { return a - b; }
    public static float mul(float a, float b) { return a * b; }
    public static float div(float a, float b) { return b != 0 ? a / b : 0; }
    public static float mod(float a, float b) { return a % b; }

    public static float abs(float a) { return a < 0 ? -a : a; }
    public static int abs(int a) { return a < 0 ? -a : a; }
    public static float sign(float a) { return a < 0 ? -1f : (a > 0 ? 1f : 0f); } // 符号(-1, 0, 1)

    // ==========================================
    // 丸め系（フロア、ラウンド、天井）
    // ==========================================
    public static int fl(float a) { return (int) Math.floor(a); } // 切り捨て
    public static int cl(float a) { return (int) Math.ceil(a); }  // 切り上げ
    public static int rd(float a) { return Math.round(a); }       // 四捨五入

    // ==========================================
    // 冪乗・ルート・指数・対数
    // ==========================================
    public static float sq(float a) { return (float) Math.sqrt(a); }     // 平方根 (√a)
    public static double sq(double a) { return Math.sqrt(a); }
    public static float pow2(float a) { return a * a; }                   // 2乗
    public static float pow(float a, float b) { return (float) Math.pow(a, b); } // a^b
    
    public static float exp(float a) { return (float) Math.exp(a); }     // e^a (ネイピア数の冪乗)
    public static float log(float a) { return (float) Math.log(a); }     // 自然対数 ln(a)
    public static float log10(float a) { return (float) Math.log10(a); } // 常用対数 log10(a)

    // ==========================================
    // 三角関数 (Trigonometry)
    // ==========================================
    public static float sin(float rad) { return (float) Math.sin(rad); }
    public static float cos(float rad) { return (float) Math.cos(rad); }
    public static float tan(float rad) { return (float) Math.tan(rad); }
    public static float asin(float a) { return (float) Math.asin(a); }
    public static float acos(float a) { return (float) Math.acos(a); }
    public static float atan(float a) { return (float) Math.atan(a); }
    public static float atan2(float y, float x) { return (float) Math.atan2(y, x); } // 角度算出用

    // 角度(度)で直接計算したい時用
    public static float sind(float deg) { return sin(deg * DEG2RAD); }
    public static float cosd(float deg) { return cos(deg * DEG2RAD); }

    // ==========================================
    // 制限・補間 (Clamp, Lerp, Map)
    // ==========================================
    public static float min(float a, float b) { return a < b ? a : b; }
    public static float max(float a, float b) { return a > b ? a : b; }
    public static int min(int a, int b) { return a < b ? a : b; }
    public static int max(int a, int b) { return a > b ? a : b; }

    /** クランプ（範囲制限） */
    public static float clp(float v, float min, float max) {
        return v < min ? min : (v > max ? max : v);
    }

    /** 線形補間 */
    public static float lr(float s, float e, float t) {
        return s + t * (e - s);
    }

    /** 値の範囲変換 (Processingのmap関数と同じ) */
    public static float map(float v, float inMin, float inMax, float outMin, float outMax) {
        return outMin + (v - inMin) * (outMax - outMin) / (inMax - inMin);
    }

    // ==========================================
    // 乱数 (Random)
    // ==========================================
    /** 0.0 ~ 1.0 のランダム */
    public static float rnd() {
        return (float) Math.random();
    }

    /** min ~ max のランダム (float) */
    public static float rnd(float min, float max) {
        return min + rnd() * (max - min);
    }

    /** min ~ max のランダム (int) */
    public static int rnd(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

    // ==========================================
    // 距離・幾何・2Dマイクラ特化
    // ==========================================
    /** 2点間距離 */
    public static float dist(float x1, float y1, float x2, float y2) {
        return sq(pow2(x2 - x1) + pow2(y2 - y1));
    }

    /** 2点間距離の2乗（sqrtを使わない高速版） */
    public static float distSq(float x1, float y1, float x2, float y2) {
        return pow2(x2 - x1) + pow2(y2 - y1);
    }

    /** ピクセル座標 → グリッドインデックス変換 */
    public static int toG(float p, int size) {
        return fl(p / size);
    }

    /** AABB当たり判定（矩形vs矩形） */
    public static boolean aabb(float x1, float y1, float w1, float h1,
                               float x2, float y2, float w2, float h2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }
}
