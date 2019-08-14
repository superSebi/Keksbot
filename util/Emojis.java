package util;

/**
 * Shayan Rais (http://shanraisshan.com)
 * created on 8/1/2016
 */
public enum Emojis {

    FIST_HAND("👊"), //https://www.emojibase.com/emoji/1f44a/fistedhandsign //http://emojipedia.org/fisted-hand-sign/
    FIST_HAND_TYPE_1_2("👊🏻"),
    FIST_HAND_TYPE_3("👊🏼"),
    FIST_HAND_TYPE_4("👊🏽"),
    FIST_HAND_TYPE_5("👊🏾"),
    FIST_HAND_TYPE_6("👊🏿"),
    RAISED_FIST("✊"), //https://www.emojibase.com/emoji/270a/raisedfist //http://emojipedia.org/raised-fist/
    RAISED_FIST_TYPE_1_2("✊🏻"),
    RAISED_FIST_TYPE_3("✊🏼"),
    RAISED_FIST_TYPE_4("✊🏽"),
    RAISED_FIST_TYPE_5("✊🏾"),
    RAISED_FIST_TYPE_6("✊🏿"),

    WOMAN_BOOTS("👢"), //https://www.emojibase.com/emoji/1f462/womansboots
    MAN_SHOE("👞"), //https://www.emojibase.com/emoji/1f45e/mansshoe
    ATHLETIC_SHOE("👟"), //https://www.emojibase.com/emoji/1f45f/athleticshoe

    //Row#: 25
    HANDBAG("👜"), //https://www.emojibase.com/emoji/1f45c/handbag

    //Row#: 26
    CLOSED_UMBRELLA("🌂"), //https://www.emojibase.com/emoji/1f302/closedumbrella

    TENNIS_RACQUET_BALL("🎾"), //https://www.emojibase.com/emoji/1f3be/tennisracquetandball
    TABLE_TENNIS_PADDLE_BALL("🏓"), //http://emojipedia.org/table-tennis-paddle-and-ball/
    BADMINTON_RACQUET_SHUTTLE_COCK("🏸"), //http://emojipedia.org/badminton-racquet-and-shuttlecock/
    ICE_HOCKEY_STICK_PUCK("🏒"), //http://emojipedia.org/ice-hockey-stick-and-puck/
    FIELD_HOCKEY_STICK_BALL("🏑"), //http://emojipedia.org/field-hockey-stick-and-ball/
    CRICKET_BAT_BALL("🏏"), //http://emojipedia.org/cricket-bat-and-ball/

    //Row#: 7

    //Row#: 8
    BOWLING("🎳"), //https://www.emojibase.com/emoji/1f3b3/bowling

   ELECTRIC_TORCH("🔦"), //https://www.emojibase.com/emoji/1f526/electrictorch
    WRENCH("🔧"), //https://www.emojibase.com/emoji/1f527/wrench
    HAMMER("🔨"), //https://www.emojibase.com/emoji/1f528/hammer

    //Row#: 8
    HAMMER_AND_PICK("⚒"), //https://www.emojibase.com/emoji/2692/hammerandpick
    HAMMER_AND_WRENCH("🛠"), //https://www.emojibase.com/emoji/1f6e0/hammerandwrench
    PICK("⛏"), //https://www.emojibase.com/emoji/26cf/pick
    NUT_AND_BOLT("🔩"), //https://www.emojibase.com/emoji/1f529/nutandbolt
    GEAR("⚙"), //https://www.emojibase.com/emoji/2699/gear
    CHAINS("⛓"), //https://www.emojibase.com/emoji/26d3/chains
    PISTOL("🔫"), //https://www.emojibase.com/emoji/1f52b/pistol
    BOMB("💣"), //https://www.emojibase.com/emoji/1f4a3/bomb

    //Row#: 9
    HOCHO("🔪"), //https://www.emojibase.com/emoji/1f52a/hocho
    DAGGER_KNIFE("🗡"), //https://www.emojibase.com/emoji/1f5e1/daggerknife
    CROSSED_WORDS("⚔"), //https://www.emojibase.com/emoji/2694/crossedswords
    SHIELD("🛡"), //https://www.emojibase.com/emoji/1f6e1/shield
    SMOKING_SYMBOL("🚬"), //https://www.emojibase.com/emoji/1f6ac/smokingsymbol
    SKULL_AND_CROSS_BONES("☠"), //https://www.emojibase.com/emoji/2620/skullandcrossbones
    //Row#: 11
    PILL("💊"), //https://www.emojibase.com/emoji/1f48a/pill
    SYRINGE("💉"), //https://www.emojibase.com/emoji/1f489/syringe
    ROLLED_UP_NEWSPAPER("🗞"), //https://www.emojibase.com/emoji/1f5de/rolledupnewspaper
    BLACK_SCISSORS("✂"), //https://www.emojibase.com/emoji/2702/blackscissors
    //Row#: 21
    PUSHPIN("📌"); //https://www.emojibase.com/emoji/1f4cc/pushpin

    //__________________________________________________________________________________________________
    private final String text;

    Emojis(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}