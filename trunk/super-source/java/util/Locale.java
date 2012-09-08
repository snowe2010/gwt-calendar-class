package java.util;

//borrowed heavily from openjdk, here's the link
//http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/Locale.java
public class Locale {

	public static final Locale CANADA;

	public static final Locale CANADA_FRENCH;

	public static final Locale CHINA;

	public static final Locale CHINESE;

	public static final Locale ENGLISH;

	public static final Locale FRANCE;

	public static final Locale FRENCH;

	public static final Locale GERMAN;

	public static final Locale GERMANY;

	public static final Locale ITALIAN;

	public static final Locale ITALY;

	public static final Locale JAPAN;

	public static final Locale JAPANESE;

	public static final Locale KOREA;

	public static final Locale KOREAN;

	public static final Locale PRC;

	public static final Locale ROOT;

	public static final Locale SIMPLIFIED_CHINESE;

	public static final Locale TAIWAN;

	public static final Locale TRADITIONAL_CHINESE;

	public static final Locale UK;

	public static final Locale US;

	public static Locale defaultLocale;

	static {

		CANADA = createSingleton("en_CA_", "en", "CA");
		CANADA_FRENCH = createSingleton("fr_CA_", "fr", "CA");
		CHINA = createSingleton("zh_CN_", "zh", "CN");
		CHINESE = createSingleton("zh__", "zh", "");
		ENGLISH = createSingleton("en__", "en", "");
		FRANCE = createSingleton("fr_FR_", "fr", "FR");
		FRENCH = createSingleton("fr__", "fr", "");
		GERMAN = createSingleton("de__", "de", "");
		GERMANY = createSingleton("de_DE_", "de", "DE");
		ITALIAN = createSingleton("it__", "it", "");
		ITALY = createSingleton("it_IT_", "it", "IT");
		JAPAN = createSingleton("ja_JP_", "ja", "JP");
		JAPANESE = createSingleton("ja__", "ja", "");
		KOREA = createSingleton("ko_KR_", "ko", "KR");
		KOREAN = createSingleton("ko__", "ko", "");
		PRC = CHINA;
		ROOT =  createSingleton("__", "", "");
		SIMPLIFIED_CHINESE =  CHINA;
		TAIWAN = createSingleton("zh_TW_", "zh", "TW");
		TRADITIONAL_CHINESE = TAIWAN;
		UK = createSingleton("en_GB_", "en", "GB");
		US =  createSingleton("en_US_", "en", "US");

	}

	// constructors

	private String language;
	private String country;
	private String variant;

	// cache to store singleton Locales
	private final static Map<String, Locale> cache = new HashMap<String, Locale>(32);

	public Locale(String language) {
		this(language, "", "");
	}

	public Locale(String language, String country) {
		this(language, country, "");
	}

	public Locale(String language, String country, String variant) {
		this.language = language;
		this.country = country;
		this.variant = variant;
	}

	// methods

	public static Locale[] getAvailableLocales() {
		// TODO, fix
		return null;
	}

	public String getCountry() {
		return country;
	}

	public static Locale getDefault() {
		// TODO, fix
		return null;
	}

	public String getDisplayCountry() {
		// TODO, fix
		return null;
	}

	public String getDisplayCountry(Locale inLocale) {
		// TODO, fix
		return null;
	}

	public String getDisplayLanguage() {
		// TODO, fix
		return null;
	}

	public String getDisplayLanguage(Locale inLocale) {
		// TODO, fix
		return null;
	}

	public String getDisplayName() {
		// TODO, fix
		return null;
	}

	public String getDisplayName(Locale inLocale) {
		// TODO, fix
		return null;
	}

	public String getDisplayVariant() {
		// TODO, fix
		return null;
	}

	public String getDisplayVariant(Locale inLocale) {
		// TODO, fix
		return null;
	}

	public String getISO3Country() {
		// TODO, fix
		return null;
	}

	public String getISO3Language() {
		// TODO, fix
		return null;
	}

	public static String[] getISOCountries() {
		// TODO, fix
		return null;
	}

	public static String[] getISOLanguages() {
		// TODO, fix
		return null;
	}

	public String getLanguage() {
		return language;
	}

	public String getVariant() {
		return variant;
	}

	public static void setDefault(Locale newLocale) {
		defaultLocale = newLocale;
	}

	private static Locale createSingleton(String key, String language, String country) {
		Locale locale = new Locale(language, country);
		cache.put(key, locale);
		return locale;
	}

}
