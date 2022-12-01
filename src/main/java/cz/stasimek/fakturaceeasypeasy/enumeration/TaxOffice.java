package cz.stasimek.fakturaceeasypeasy.enumeration;

import java.text.Collator;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public enum TaxOffice {
	PRAHA_1(2001, "Územní pracoviště pro Prahu 1", 451),
	PRAHA_2(2002, "Územní pracoviště pro Prahu 2", 451),
	PRAHA_3(2003, "Územní pracoviště pro Prahu 3", 451),
	PRAHA_4(2004, "Územní pracoviště pro Prahu 4", 451),
	PRAHA_5(2005, "Územní pracoviště pro Prahu 5", 451),
	PRAHA_6(2006, "Územní pracoviště pro Prahu 6", 451),
	PRAHA_7(2007, "Územní pracoviště pro Prahu 7", 451),
	PRAHA_8(2008, "Územní pracoviště pro Prahu 8", 451),
	PRAHA_9(2009, "Územní pracoviště pro Prahu 9", 451),
	PRAHA_10(2010, "Územní pracoviště pro Prahu 10", 451),
	PRAHA_JIZNI_MESTO(2011, "Územní pracoviště pro Prahu - Jižní Město", 451),
	PRAHA_MODRANY(2012, "Územní pracoviště v Praze - Modřanech", 451),
	PRAHA_VYCHOD(2101, "Územní pracoviště Praha-východ", 452),
	PRAHA_ZAPAD(2102, "Územní pracoviště Praha-západ", 452),
	BENESOV(2103, "Územní pracoviště v Benešově", 452),
	BEROUN(2104, "Územní pracoviště v Berouně", 452),
	BRANDYS_NAD_LABEM(2105, "Územní pracoviště v Brandýse n.L.-Staré Boleslavi", 452),
	CASLAV(2106, "Územní pracoviště v Čáslavi", 452),
	CESKY_BROD(2107, "Územní pracoviště v Českém Brodě", 452),
	DOBRIS(2108, "Územní pracoviště v Dobříši", 452),
	HOROVICE(2109, "Územní pracoviště v Hořovicích", 452),
	KLADNO(2110, "Územní pracoviště v Kladně", 452),
	KOLIN(2111, "Územní pracoviště v Kolíně", 452),
	KRALUPY_NAD_VLTAVOU(2112, "Územní pracoviště v Kralupech nad Vltavou", 452),
	KUTNA_HORA(2113, "Územní pracoviště v Kutné Hoře", 452),
	MELNIK(2114, "Územní pracoviště v Mělníce", 452),
	MLADA_BOLESLAV(2115, "Územní pracoviště v Mladé Boleslavi", 452),
	MNICHOVO_HRADISTE(2116, "Územní pracoviště v Mnichově Hradišti", 452),
	NERATOVICE(2117, "Územní pracoviště v Neratovicích", 452),
	NYMBURK(2118, "Územní pracoviště v Nymburku", 452),
	PODEBRADY(2119, "Územní pracoviště v Poděbradech", 452),
	PRIBRAM(2120, "Územní pracoviště v Příbrami", 452),
	RAKOVNIK(2121, "Územní pracoviště v Rakovníku", 452),
	RICANY(2122, "Územní pracoviště v Říčanech", 452),
	SEDLCANY(2123, "Územní pracoviště v Sedlčanech", 452),
	SLANY(2124, "Územní pracoviště ve Slaném", 452),
	VLASIM(2125, "Územní pracoviště ve Vlašimi", 452),
	VOTICE(2126, "Územní pracoviště ve Voticích", 452),
	CESKE_BUDEJOVICE(2201, "Územní pracoviště v Českých Budějovicích", 453),
	BLATNA(2202, "Územní pracoviště v Blatné", 453),
	CESKY_KRUMLOV(2203, "Územní pracoviště v Českém Krumlově", 453),
	DACICE(2204, "Územní pracoviště v Dačicích", 453),
	JINDRICHUV_HRADEC(2205, "Územní pracoviště v Jindřichově Hradci", 453),
	KAPLICE(2206, "Územní pracoviště v Kaplici", 453),
	MILEVSKO(2207, "Územní pracoviště v Milevsku", 453),
	PISEK(2208, "Územní pracoviště v Písku", 453),
	PRACHATICE(2209, "Územní pracoviště v Prachaticích", 453),
	SOBESLAV(2210, "Územní pracoviště v Soběslavi", 453),
	STRAKONICE(2211, "Územní pracoviště ve Strakonicích", 453),
	TABOR(2212, "Územní pracoviště v Táboře", 453),
	TRHOVE_SVINY(2213, "Územní pracoviště v Trhových Svinech", 453),
	TREBON(2214, "Územní pracoviště v Třeboni", 453),
	TYN_NAD_VLTAVOU(2215, "Územní pracoviště v Týně nad Vltavou", 453),
	VIMPERK(2216, "Územní pracoviště ve Vimperku", 453),
	VODNANY(2217, "Územní pracoviště ve Vodňanech", 453),
	PLZEN(2301, "Územní pracoviště v Plzni", 454),
	PLZEN_SEVER(2302, "Územní pracoviště Plzeň-sever", 454),
	PLZEN_JIH(2303, "Územní pracoviště Plzeň-jih", 454),
	BLOVICE(2304, "Územní pracoviště v Blovicích", 454),
	DOMAZLICE(2305, "Územní pracoviště v Domažlicích", 454),
	HORAZDOVICE(2306, "Územní pracoviště v Horažďovicích", 454),
	HORSOVSKY_TYN(2307, "Územní pracoviště v Horšovském Týně", 454),
	KLATOVY(2308, "Územní pracoviště v Klatovech", 454),
	KRALOVICE(2309, "Územní pracoviště v Kralovicích", 454),
	NEPOMUK(2310, "Územní pracoviště v Nepomuku", 454),
	PRESTICE(2311, "Územní pracoviště v Přešticích", 454),
	ROKYCANY(2312, "Územní pracoviště v Rokycanech", 454),
	TACHOV(2313, "Územní pracoviště v Tachově", 454),
	STRIBRO(2314, "Územní pracoviště ve Stříbře", 454),
	SUSICE(2315, "Územní pracoviště v Sušici", 454),
	KARLOVY_VARY(2401, "Územní pracoviště v Karlových Varech", 455),
	AS(2402, "Územní pracoviště v Aši", 455),
	CHEB(2403, "Územní pracoviště v Chebu", 455),
	KRASLICE(2404, "Územní pracoviště v Kraslicích", 455),
	MARIANSKE_LAZNE(2405, "Územní pracoviště v Mariánských Lázních", 455),
	OSTROV(2406, "Územní pracoviště v Ostrově", 455),
	SOKOLOV(2407, "Územní pracoviště v Sokolově", 455),
	USTI_NAD_LABEM(2501, "Územní pracoviště v Ústí nad Labem", 456),
	BILINA(2502, "Územní pracoviště v Bílině", 456),
	DECIN(2503, "Územní pracoviště v Děčíně", 456),
	CHOMUTOV(2504, "Územní pracoviště v Chomutově", 456),
	KADAN(2505, "Územní pracoviště v Kadani", 456),
	LIBOCHOVICE(2506, "Územní pracoviště v Libochovicích", 456),
	LITOMERICE(2507, "Územní pracoviště v Litoměřicích", 456),
	LITVINOV(2508, "Územní pracoviště v Litvínově", 456),
	LOUNY(2509, "Územní pracoviště v Lounech", 456),
	MOST(2510, "Územní pracoviště v Mostě", 456),
	PODBORANY(2511, "Územní pracoviště v Podbořanech", 456),
	ROUDNICE_NAD_LABEM(2512, "Územní pracoviště v Roudnici nad Labem", 456),
	RUMBURK(2513, "Územní pracoviště v Rumburku", 456),
	TEPLICE(2514, "Územní pracoviště v Teplicích", 456),
	ZATEC(2515, "Územní pracoviště v Žatci", 456),
	LIBEREC(2601, "Územní pracoviště v Liberci", 457),
	CESKA_LIPA(2602, "Územní pracoviště v České Lípě", 457),
	FRYDLANT(2603, "Územní pracoviště ve Frýdlantě", 457),
	JABLONEC_NAD_NISOU(2604, "Územní pracoviště v Jablonci nad Nisou", 457),
	JILEMICE(2605, "Územní pracoviště v Jilemnici", 457),
	NOVY_BOR(2606, "Územní pracoviště v Novém Boru", 457),
	SEMILY(2607, "Územní pracoviště v Semilech", 457),
	TANVALD(2608, "Územní pracoviště v Tanvaldě", 457),
	TURNOV(2609, "Územní pracoviště v Turnově", 457),
	ZELEZNY_BROD(2610, "Územní pracoviště v Železném Brodě", 457),
	HRADEC_KRALOVE(2701, "Územní pracoviště v Hradci Králové", 458),
	BROUMOV(2702, "Územní pracoviště v Broumově", 458),
	DOBRUSKA(2703, "Územní pracoviště v Dobrušce", 458),
	DVUR_KRALOVE_NAD_LABEM(2704, "Územní pracoviště ve Dvoře Králové nad Labem", 458),
	HORICE(2705, "Územní pracoviště v Hořicích", 458),
	JAROMER(2706, "Územní pracoviště v Jaroměři", 458),
	JICIN(2707, "Územní pracoviště v Jičíně", 458),
	KOSTELEC_NAD_ORLICI(2708, "Územní pracoviště v Kostelci nad Orlicí", 458),
	NACHOD(2709, "Územní pracoviště v Náchodě", 458),
	NOVA_PAKA(2710, "Územní pracoviště v Nové Pace", 458),
	NOVY_BYDZOV(2711, "Územní pracoviště v Novém Bydžově", 458),
	RYCHNOV_NAD_KNEZNOU(2712, "Územní pracoviště v Rychnově nad Kněžnou", 458),
	TRUTNOV(2713, "Územní pracoviště v Trutnově", 458),
	VRCHLABI(2714, "Územní pracoviště ve Vrchlabí", 458),
	PARDUBICE(2801, "Územní pracoviště v Pardubicích", 459),
	HLINSKO(2802, "Územní pracoviště v Hlinsku", 459),
	HOLICE(2803, "Územní pracoviště v Holicích", 459),
	CHRUDIM(2804, "Územní pracoviště v Chrudimi", 459),
	LITOMYSL(2805, "Územní pracoviště v Litomyšli", 459),
	MORAVSKA_TREBOVA(2806, "Územní pracoviště v Moravské Třebové", 459),
	PRELOUC(2807, "Územní pracoviště v Přelouči", 459),
	SVITAVY(2808, "Územní pracoviště ve Svitavách", 459),
	USTI_NAD_ORLICI(2809, "Územní pracoviště v Ústí nad Orlicí", 459),
	VYSOKE_MYTO(2810, "Územní pracoviště ve Vysokém Mýtě", 459),
	ZAMBERK(2811, "Územní pracoviště v Žamberku", 459),
	JIHLAVA(2901, "Územní pracoviště v Jihlavě", 460),
	BYSTRICE_NAD_PERNSTEJNEM(2902, "Územní pracoviště v Bystřici nad Pernštejnem", 460),
	HAVLICKUV_BROD(2903, "Územní pracoviště v Havlíčkově Brodě", 460),
	HUMPOLEC(2904, "Územní pracoviště v Humpolci", 460),
	CHOTEBOR(2905, "Územní pracoviště v Chotěboři", 460),
	LEDEC_NAD_SAZAVOU(2906, "Územní pracoviště v Ledči nad Sázavou", 460),
	MORAVSKE_BUDEJOVICE(2907, "Územní pracoviště v Moravských Budějovicích", 460),
	NAMEST_NAD_OSLAVOU(2908, "Územní pracoviště v Náměšti nad Oslavou", 460),
	PACOV(2909, "Územní pracoviště v Pacově", 460),
	PELHRIMOV(2910, "Územní pracoviště v Pelhřimově", 460),
	TELC(2911, "Územní pracoviště v Telči", 460),
	TREBIC(2912, "Územní pracoviště v Třebíči", 460),
	VELKE_MEZIRICI(2913, "Územní pracoviště ve Velkém Meziříčí", 460),
	ZDAR_NAD_SAZAVOU(2914, "Územní pracoviště ve Žďáru nad Sázavou", 460),
	BRNO_I(3001, "Územní pracoviště Brno I", 461),
	BRNO_II(3002, "Územní pracoviště Brno II", 461),
	BRNO_III(3003, "Územní pracoviště Brno III", 461),
	BRNO_IV(3004, "Územní pracoviště Brno IV", 461),
	BRNO_VENKOV(3005, "Územní pracoviště Brno-venkov", 461),
	BLANSKO(3006, "Územní pracoviště v Blansku", 461),
	BOSKOVICE(3007, "Územní pracoviště v Boskovicích", 461),
	BRECLAV(3008, "Územní pracoviště v Břeclavi", 461),
	BUCOVICE(3009, "Územní pracoviště v Bučovicích", 461),
	HODONIN(3010, "Územní pracoviště v Hodoníně", 461),
	HUSTOPECE(3011, "Územní pracoviště v Hustopečích", 461),
	IVANCICE(3012, "Územní pracoviště v Ivančicích", 461),
	KYJOV(3013, "Územní pracoviště v Kyjově", 461),
	MIKULOV(3014, "Územní pracoviště v Mikulově", 461),
	MORAVSKY_KRUMLOV(3015, "Územní pracoviště v Moravském Krumlově", 461),
	SLAVKOV_U_BRNA(3016, "Územní pracoviště ve Slavkově u Brna", 461),
	TISNOV(3017, "Územní pracoviště v Tišnově", 461),
	VESELI_NAD_MORAVOU(3018, "Územní pracoviště ve Veselí nad Moravou", 461),
	VYSKOV(3019, "Územní pracoviště ve Vyškově", 461),
	ZNOJMO(3020, "Územní pracoviště ve Znojmě", 461),
	OLOMOUC(3101, "Územní pracoviště v Olomouci", 462),
	HRANICE(3102, "Územní pracoviště v Hranicích", 462),
	JESENIK(3103, "Územní pracoviště v Jeseníku", 462),
	KONICE(3104, "Územní pracoviště v Konici", 462),
	LITOVEL(3105, "Územní pracoviště v Litovli", 462),
	PROSTEJOV(3106, "Územní pracoviště v Prostějově", 462),
	PREROV(3107, "Územní pracoviště v Přerově", 462),
	STERNBERK(3108, "Územní pracoviště ve Šternberku", 462),
	SUMPERK(3109, "Územní pracoviště v Šumperku", 462),
	ZABREH(3110, "Územní pracoviště v Zábřehu", 462),
	OSTRAVA_I(3201, "Územní pracoviště Ostrava I", 463),
	OSTRAVA_II(3202, "Územní pracoviště Ostrava II", 463),
	OSTRAVA_III(3203, "Územní pracoviště Ostrava III", 463),
	BOHUMIN(3204, "Územní pracoviště v Bohumíně", 463),
	BRUNTAL(3205, "Územní pracoviště v Bruntále", 463),
	CESKY_TESIN(3206, "Územní pracoviště v Českém Těšíně", 463),
	FRYDEK_MISTEK(3207, "Územní pracoviště ve Frýdku-Místku", 463),
	FRYDLANT_NAD_OSTRAVICI(3208, "Územní pracoviště ve Frýdlantě nad Ostravicí", 463),
	FULNEK(3209, "Územní pracoviště ve Fulneku", 463),
	HAVIROV(3210, "Územní pracoviště v Havířově", 463),
	HLUCIN(3211, "Územní pracoviště v Hlučíně", 463),
	KARVINA(3212, "Územní pracoviště v Karviné", 463),
	KOPRIVNICE(3213, "Územní pracoviště v Kopřivnici", 463),
	KRNOV(3214, "Územní pracoviště v Krnově", 463),
	NOVY_JICIN(3215, "Územní pracoviště v Novém Jičíně", 463),
	OPAVA(3216, "Územní pracoviště v Opavě", 463),
	ORLOVA(3217, "Územní pracoviště v Orlové", 463),
	TRINEC(3218, "Územní pracoviště v Třinci", 463),
	ZLIN(3301, "Územní pracoviště ve Zlíně", 464),
	BYSTRICE_POD_HOSTYNEM(3302, "Územní pracoviště v Bystřici pod Hostýnem", 464),
	HOLESOV(3303, "Územní pracoviště v Holešově", 464),
	KROMERIZ(3304, "Územní pracoviště v Kroměříži", 464),
	LUHACOVICE(3305, "Územní pracoviště v Luhačovicích", 464),
	OTROKOVICE(3306, "Územní pracoviště v Otrokovicích", 464),
	ROZNOV_POD_RADHOSTEM(3307, "Územní pracoviště v Rožnově pod Radhoštěm", 464),
	UHERSKY_BROD(3308, "Územní pracoviště v Uherském Brodě", 464),
	UHERSKE_HRADISTE(3309, "Územní pracoviště v Uherském Hradišti", 464),
	VALASSKE_MEZIRICI(3310, "Územní pracoviště ve Valašském Meziříčí", 464),
	VALASSKE_KLOBOUKY(3311, "Územní pracoviště ve Valašských Kloboukách", 464),
	VSETIN(3312, "Územní pracoviště ve Vsetíně", 464),
	SPECIALIZED_TO_CESKE_BUDEJOVICE(4022, "Specializovaný finanční úřad - územní pracoviště v Českých Budějovicích", 13),
	SPECIALIZED_TO_PLZEN(4023, "Specializovaný finanční úřad - územní pracoviště v Plzni", 13),
	SPECIALIZED_TO_USTI_NAD_LABEM(4025, "Specializovaný finanční úřad - územní pracoviště v Ústí nad Labem", 13),
	SPECIALIZED_TO_HRADEC_KRALOVE(4027, "Specializovaný finanční úřad - územní pracoviště v Hradci Králové", 13),
	SPECIALIZED_TO_BRNO(4030, "Specializovaný finanční úřad - územní pracoviště v Brně", 13),
	SPECIALIZED_TO_OSTRAVA(4032, "Specializovaný finanční úřad - územní pracoviště v Ostravě", 13);

	private final int regionalTaxOfficeId;
	private final String regionalTaxOfficeName;
	private final int taxOfficeId;

	TaxOffice(int regionalTaxOfficeId, String regionalTaxOfficeName, int taxOfficeId) {
		this.regionalTaxOfficeId = regionalTaxOfficeId;
		this.regionalTaxOfficeName = regionalTaxOfficeName;
		this.taxOfficeId = taxOfficeId;
	}

	public static Map<String, String> valuesMap() {
		return Arrays.stream(TaxOffice.values())
				.map((TaxOffice t) -> new String[]{t.name(), t.getRegionalTaxOfficeName()})
				.sorted((x, y) -> {
					// Order items by name (specialized will be last)
					if (x[0].startsWith("SPECIALIZED_TO_") && !y[0].startsWith("SPECIALIZED_TO_")) {
						return 1;
					}
					if (!x[0].startsWith("SPECIALIZED_TO_") && y[0].startsWith("SPECIALIZED_TO_")) {
						return -1;
					}
					String cityX = x[1].replaceFirst("Územní pracoviště (?:v|ve|pro)? ?(.+)", "$1");
					String cityY = y[1].replaceFirst("Územní pracoviště (?:v|ve|pro)? ?(.+)", "$1");
					Locale czech = Locale.of("cs", "CZ");
					Collator collator = Collator.getInstance(czech); // Collator can sort Czech strings
					return collator.compare(cityX, cityY);
				})
				.collect(Collectors.toMap(p -> p[0], p -> p[1], (x, y) -> y, LinkedHashMap::new));
	}
}
