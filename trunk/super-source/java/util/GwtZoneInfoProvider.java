package java.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.client.TimeZoneInfo;
import com.google.gwt.i18n.client.constants.TimeZoneConstants;

//TODO -sf- move this to the src package!
class GwtZoneInfoProvider {

    private TimeZoneConstants timeZoneConstants;
    private Map<String,TimeZoneContainer> map = new HashMap<String,TimeZoneContainer>();

    private static GwtZoneInfoProvider singleton;

    public static GwtZoneInfoProvider singleton() {
        if (singleton == null) {
            singleton = new GwtZoneInfoProvider();
        }
        return singleton;
    }

    GwtZoneInfoProvider() {
        timeZoneConstants = (TimeZoneConstants) GWT.create(TimeZoneConstants.class);



        // http://google-web-toolkit.googlecode.com/svn/trunk/user/src/com/google/gwt/i18n/client/constants/TimeZoneConstants.properties
        // awk '{print "add(timeZoneConstants."$1"());" }' tz_constants.txt

        for(int i = -14; i < 13; i++ )
        {
        	String sign = i < 0 ? "" : "+";
        	String name = "Etc/GMT" + sign + i;
        	map.put(name,new TimeZoneContainer(name, null, TimeZone.createTimeZone(i * 60), null));
        }

        add(timeZoneConstants.atlanticCanary());
        add(timeZoneConstants.australiaMelbourne());
        add(timeZoneConstants.antarcticaMawson());
        add(timeZoneConstants.americaNipigon());
        add(timeZoneConstants.americaMiquelon());
        add(timeZoneConstants.pacificWallis());
        add(timeZoneConstants.europeSkopje());
        add(timeZoneConstants.americaCoralHarbour());
        add(timeZoneConstants.asiaDhaka());
        add(timeZoneConstants.americaStLucia());
        add(timeZoneConstants.asiaKashgar());
        add(timeZoneConstants.americaPhoenix());
        add(timeZoneConstants.asiaKuwait());
        add(timeZoneConstants.asiaHongKong());
        add(timeZoneConstants.antarcticaRothera());
        add(timeZoneConstants.europeStockholm());
        add(timeZoneConstants.pacificFiji());
        add(timeZoneConstants.pacificApia());
        add(timeZoneConstants.pacificPagoPago());
        add(timeZoneConstants.asiaRangoon());
        add(timeZoneConstants.americaMexicoCity());
        add(timeZoneConstants.americaPuertoRico());
        add(timeZoneConstants.indianMauritius());
        add(timeZoneConstants.europeBerlin());
        add(timeZoneConstants.europeZurich());
        add(timeZoneConstants.americaBelem());
        add(timeZoneConstants.antarcticaDavis());
        add(timeZoneConstants.asiaKrasnoyarsk());
        add(timeZoneConstants.atlanticBermuda());
        add(timeZoneConstants.australiaCurrie());
        add(timeZoneConstants.asiaTehran());
        add(timeZoneConstants.asiaSaigon());
        add(timeZoneConstants.asiaBaku());
        add(timeZoneConstants.americaDanmarkshavn());
        add(timeZoneConstants.americaScoresbysund());
        add(timeZoneConstants.americaEirunepe());
        add(timeZoneConstants.americaCaracas());
        add(timeZoneConstants.asiaBaghdad());
        add(timeZoneConstants.africaMonrovia());
        add(timeZoneConstants.americaStVincent());
        add(timeZoneConstants.americaVancouver());
        add(timeZoneConstants.asiaThimphu());
        add(timeZoneConstants.africaAccra());
        add(timeZoneConstants.americaBelize());
        add(timeZoneConstants.americaEdmonton());
        add(timeZoneConstants.asiaTashkent());
        add(timeZoneConstants.asiaTokyo());
        add(timeZoneConstants.pacificKiritimati());
        add(timeZoneConstants.australiaSydney());
        add(timeZoneConstants.europeRiga());
        add(timeZoneConstants.asiaDili());
        add(timeZoneConstants.africaMbabane());
        add(timeZoneConstants.asiaOral());
        add(timeZoneConstants.asiaAden());
        add(timeZoneConstants.europeIstanbul());
        add(timeZoneConstants.africaAbidjan());
        add(timeZoneConstants.australiaLindeman());
        add(timeZoneConstants.pacificGalapagos());
        add(timeZoneConstants.americaBogota());
        add(timeZoneConstants.americaDawson());
        add(timeZoneConstants.americaChicago());
        add(timeZoneConstants.pacificKwajalein());
        add(timeZoneConstants.australiaBrokenHill());
        add(timeZoneConstants.americaCuiaba());
        add(timeZoneConstants.indianChristmas());
        add(timeZoneConstants.asiaJayapura());
        add(timeZoneConstants.europeBrussels());
        add(timeZoneConstants.europeLisbon());
        add(timeZoneConstants.asiaChongqing());
        add(timeZoneConstants.americaNoronha());
        add(timeZoneConstants.europeMadrid());
        add(timeZoneConstants.africaAlgiers());
        add(timeZoneConstants.africaHarare());
        add(timeZoneConstants.africaNdjamena());
        add(timeZoneConstants.americaCostaRica());
        add(timeZoneConstants.europeLjubljana());
        add(timeZoneConstants.indianMayotte());
        add(timeZoneConstants.asiaPhnomPenh());
        add(timeZoneConstants.americaManagua());
        add(timeZoneConstants.asiaBrunei());
        add(timeZoneConstants.americaTijuana());
        add(timeZoneConstants.pacificFakaofo());
        add(timeZoneConstants.americaAdak());
        add(timeZoneConstants.americaAntigua());
        add(timeZoneConstants.americaArgentinaLaRioja());
        add(timeZoneConstants.pacificTahiti());
        add(timeZoneConstants.americaPangnirtung());
        add(timeZoneConstants.europeZagreb());
        add(timeZoneConstants.americaAsuncion());
        add(timeZoneConstants.europeVienna());
        add(timeZoneConstants.australiaHobart());
        add(timeZoneConstants.americaJuneau());
        add(timeZoneConstants.americaInuvik());
        add(timeZoneConstants.americaMontreal());
        add(timeZoneConstants.asiaSeoul());
        add(timeZoneConstants.indianComoro());
        add(timeZoneConstants.europeParis());
        add(timeZoneConstants.europeTallinn());
        add(timeZoneConstants.indianMahe());
        add(timeZoneConstants.asiaCalcutta());
        add(timeZoneConstants.americaMartinique());
        add(timeZoneConstants.asiaSingapore());
        add(timeZoneConstants.africaNairobi());
        add(timeZoneConstants.americaMaceio());
        add(timeZoneConstants.africaCairo());
        add(timeZoneConstants.europeMoscow());
        add(timeZoneConstants.antarcticaPalmer());
        add(timeZoneConstants.asiaUlaanbaatar());
        add(timeZoneConstants.americaRainyRiver());
        add(timeZoneConstants.indianMaldives());
        add(timeZoneConstants.asiaColombo());
        add(timeZoneConstants.australiaAdelaide());
        add(timeZoneConstants.americaCambridgeBay());
        add(timeZoneConstants.africaLuanda());
        add(timeZoneConstants.pacificChatham());
        add(timeZoneConstants.americaCordoba());
        add(timeZoneConstants.asiaTbilisi());
        add(timeZoneConstants.europeGibraltar());
        add(timeZoneConstants.asiaKarachi());
        add(timeZoneConstants.asiaHarbin());
        add(timeZoneConstants.australiaLordHowe());
        add(timeZoneConstants.americaBoaVista());
        add(timeZoneConstants.africaTripoli());
        add(timeZoneConstants.indianReunion());
        add(timeZoneConstants.atlanticStanley());
        add(timeZoneConstants.americaBlancSablon());
        add(timeZoneConstants.americaSantoDomingo());
        add(timeZoneConstants.antarcticaSyowa());
        add(timeZoneConstants.americaJamaica());
        add(timeZoneConstants.europeKiev());
        add(timeZoneConstants.europeBudapest());
        add(timeZoneConstants.pacificMidway());
        add(timeZoneConstants.americaGooseBay());
        add(timeZoneConstants.asiaAmman());
        add(timeZoneConstants.asiaSakhalin());
        add(timeZoneConstants.africaWindhoek());
        add(timeZoneConstants.asiaKatmandu());
        add(timeZoneConstants.americaGuyana());
        add(timeZoneConstants.americaSaoPaulo());
        add(timeZoneConstants.australiaPerth());
        add(timeZoneConstants.africaDjibouti());
        add(timeZoneConstants.asiaJakarta());
        add(timeZoneConstants.asiaPyongyang());
        add(timeZoneConstants.africaJohannesburg());
        add(timeZoneConstants.asiaIrkutsk());
        add(timeZoneConstants.africaNiamey());
        add(timeZoneConstants.africaCasablanca());
        add(timeZoneConstants.asiaBishkek());
        add(timeZoneConstants.africaNouakchott());
        add(timeZoneConstants.europeVilnius());
        add(timeZoneConstants.americaCayenne());
        add(timeZoneConstants.africaMogadishu());
        add(timeZoneConstants.americaKentuckyMonticello());
        add(timeZoneConstants.americaRioBranco());
        add(timeZoneConstants.americaCancun());
        add(timeZoneConstants.americaHavana());
        add(timeZoneConstants.pacificGuam());
        add(timeZoneConstants.pacificKosrae());
        add(timeZoneConstants.atlanticAzores());
        add(timeZoneConstants.australiaEucla());
        add(timeZoneConstants.asiaShanghai());
        add(timeZoneConstants.americaRankinInlet());
        add(timeZoneConstants.americaIqaluit());
        add(timeZoneConstants.africaMaputo());
        add(timeZoneConstants.asiaBahrain());
        add(timeZoneConstants.asiaAshgabat());
        add(timeZoneConstants.asiaRiyadh());
        add(timeZoneConstants.atlanticFaeroe());
        add(timeZoneConstants.europeWarsaw());
        add(timeZoneConstants.americaAnguilla());
        add(timeZoneConstants.asiaDamascus());
        add(timeZoneConstants.americaNorthDakotaCenter());
        add(timeZoneConstants.americaIndianaVevay());
        add(timeZoneConstants.atlanticStHelena());
        add(timeZoneConstants.americaBarbados());
        add(timeZoneConstants.americaIndianaVincennes());
        add(timeZoneConstants.asiaAlmaty());
        add(timeZoneConstants.africaLome());
        add(timeZoneConstants.africaBrazzaville());
        add(timeZoneConstants.americaNome());
        add(timeZoneConstants.asiaTaipei());
        add(timeZoneConstants.americaYakutat());
        add(timeZoneConstants.americaAraguaina());
        add(timeZoneConstants.europeVaduz());
        add(timeZoneConstants.africaAsmera());
        add(timeZoneConstants.europeMinsk());
        add(timeZoneConstants.americaBuenosAires());
        add(timeZoneConstants.africaMaseru());
        add(timeZoneConstants.americaStJohns());
        add(timeZoneConstants.asiaKuching());
        add(timeZoneConstants.africaLibreville());
        add(timeZoneConstants.africaFreetown());
        add(timeZoneConstants.africaBissau());
        add(timeZoneConstants.europeSamara());
        add(timeZoneConstants.europeAmsterdam());
        add(timeZoneConstants.europeTirane());
        add(timeZoneConstants.pacificSaipan());
        add(timeZoneConstants.asiaMagadan());
        add(timeZoneConstants.europeZaporozhye());
        add(timeZoneConstants.americaElSalvador());
        add(timeZoneConstants.europePodgorica());
        add(timeZoneConstants.americaSantiago());
        add(timeZoneConstants.americaAruba());
        add(timeZoneConstants.americaIndianapolis());
        add(timeZoneConstants.americaRegina());
        add(timeZoneConstants.pacificTruk());
        add(timeZoneConstants.pacificFunafuti());
        add(timeZoneConstants.americaMerida());
        add(timeZoneConstants.americaGuatemala());
        add(timeZoneConstants.africaSaoTome());
        add(timeZoneConstants.asiaMakassar());
        add(timeZoneConstants.africaBujumbura());
        add(timeZoneConstants.europeChisinau());
        add(timeZoneConstants.americaMonterrey());
        add(timeZoneConstants.asiaYekaterinburg());
        add(timeZoneConstants.antarcticaCasey());
        add(timeZoneConstants.pacificEnderbury());
        add(timeZoneConstants.americaThule());
        add(timeZoneConstants.americaLouisville());
        add(timeZoneConstants.americaMoncton());
        add(timeZoneConstants.europeHelsinki());
        add(timeZoneConstants.atlanticCapeVerde());
        add(timeZoneConstants.americaTegucigalpa());
        add(timeZoneConstants.indianCocos());
        add(timeZoneConstants.americaBoise());
        add(timeZoneConstants.americaGuadeloupe());
        add(timeZoneConstants.americaNassau());
        add(timeZoneConstants.europePrague());
        add(timeZoneConstants.americaHalifax());
        add(timeZoneConstants.asiaHovd());
        add(timeZoneConstants.americaManaus());
        add(timeZoneConstants.americaGodthab());
        add(timeZoneConstants.americaCatamarca());
        add(timeZoneConstants.americaChihuahua());
        add(timeZoneConstants.asiaBeirut());
        add(timeZoneConstants.americaArgentinaRioGallegos());
        add(timeZoneConstants.pacificGambier());
        add(timeZoneConstants.europeVolgograd());
        add(timeZoneConstants.africaBamako());
        add(timeZoneConstants.europeUzhgorod());
        add(timeZoneConstants.africaBanjul());
        add(timeZoneConstants.asiaAqtau());
        add(timeZoneConstants.africaMalabo());
        add(timeZoneConstants.atlanticMadeira());
        add(timeZoneConstants.pacificNoumea());
        add(timeZoneConstants.africaKinshasa());
        add(timeZoneConstants.europeMalta());
        add(timeZoneConstants.americaArgentinaUshuaia());
        add(timeZoneConstants.asiaBangkok());
        add(timeZoneConstants.pacificNiue());
        add(timeZoneConstants.australiaBrisbane());
        add(timeZoneConstants.americaRecife());
        add(timeZoneConstants.asiaYerevan());
        add(timeZoneConstants.americaLaPaz());
        add(timeZoneConstants.asiaUrumqi());
        add(timeZoneConstants.africaLusaka());
        add(timeZoneConstants.pacificGuadalcanal());
        add(timeZoneConstants.americaYellowknife());
        add(timeZoneConstants.asiaVientiane());
        add(timeZoneConstants.europeKaliningrad());
        add(timeZoneConstants.africaConakry());
        add(timeZoneConstants.americaArgentinaTucuman());
        add(timeZoneConstants.europeOslo());
        add(timeZoneConstants.americaStKitts());
        add(timeZoneConstants.americaPanama());
        add(timeZoneConstants.americaHermosillo());
        add(timeZoneConstants.pacificPalau());
        add(timeZoneConstants.americaGuayaquil());
        add(timeZoneConstants.asiaKualaLumpur());
        add(timeZoneConstants.europeLondon());
        add(timeZoneConstants.americaMenominee());
        add(timeZoneConstants.asiaKamchatka());
        add(timeZoneConstants.asiaVladivostok());
        add(timeZoneConstants.asiaQatar());
        add(timeZoneConstants.asiaDubai());
        add(timeZoneConstants.asiaYakutsk());
        add(timeZoneConstants.asiaOmsk());
        add(timeZoneConstants.africaBangui());
        add(timeZoneConstants.americaParamaribo());
        add(timeZoneConstants.africaLubumbashi());
        add(timeZoneConstants.pacificMarquesas());
        add(timeZoneConstants.europeBratislava());
        add(timeZoneConstants.asiaAnadyr());
        add(timeZoneConstants.americaNewYork());
        add(timeZoneConstants.pacificNorfolk());
        add(timeZoneConstants.pacificRarotonga());
        add(timeZoneConstants.americaDominica());
        add(timeZoneConstants.africaPortoNovo());
        add(timeZoneConstants.asiaSamarkand());
        add(timeZoneConstants.asiaDushanbe());
        add(timeZoneConstants.americaToronto());
        add(timeZoneConstants.americaBahia());
        add(timeZoneConstants.africaKampala());
        add(timeZoneConstants.africaOuagadougou());
        add(timeZoneConstants.asiaMuscat());
        add(timeZoneConstants.americaPortofSpain());
        add(timeZoneConstants.pacificWake());
        add(timeZoneConstants.australiaDarwin());
        add(timeZoneConstants.americaWhitehorse());
        add(timeZoneConstants.americaSwiftCurrent());
        add(timeZoneConstants.europeCopenhagen());
        add(timeZoneConstants.americaMontserrat());
        add(timeZoneConstants.americaMendoza());
        add(timeZoneConstants.europeSimferopol());
        add(timeZoneConstants.africaBlantyre());
        add(timeZoneConstants.americaDetroit());
        add(timeZoneConstants.americaShiprock());
        add(timeZoneConstants.americaGrenada());
        add(timeZoneConstants.americaIndianaPetersburg());
        add(timeZoneConstants.asiaPontianak());
        add(timeZoneConstants.europeAthens());
        add(timeZoneConstants.americaPortauPrince());
        add(timeZoneConstants.americaCayman());
        add(timeZoneConstants.africaDaresSalaam());
        add(timeZoneConstants.americaCuracao());
        add(timeZoneConstants.indianKerguelen());
        add(timeZoneConstants.africaKhartoum());
        add(timeZoneConstants.asiaManila());
        add(timeZoneConstants.europeSarajevo());
        add(timeZoneConstants.americaJujuy());
        add(timeZoneConstants.africaDouala());
        add(timeZoneConstants.europeRome());
        add(timeZoneConstants.americaArgentinaSanJuan());
        add(timeZoneConstants.americaNorthDakotaNewSalem());
        add(timeZoneConstants.pacificPortMoresby());
        add(timeZoneConstants.europeAndorra());
        add(timeZoneConstants.europeLuxembourg());
        add(timeZoneConstants.pacificHonolulu());
        add(timeZoneConstants.americaStThomas());
        add(timeZoneConstants.pacificMajuro());
        add(timeZoneConstants.americaMazatlan());
        add(timeZoneConstants.asiaMacau());
        add(timeZoneConstants.europeBelgrade());
        add(timeZoneConstants.asiaChoibalsan());
        add(timeZoneConstants.antarcticaMcMurdo());
        add(timeZoneConstants.americaThunderBay());
        add(timeZoneConstants.americaLosAngeles());
        add(timeZoneConstants.asiaKabul());
        add(timeZoneConstants.indianAntananarivo());
        add(timeZoneConstants.atlanticReykjavik());
        add(timeZoneConstants.asiaNicosia());
        add(timeZoneConstants.pacificPonape());
        add(timeZoneConstants.pacificTongatapu());
        add(timeZoneConstants.pacificPitcairn());
        add(timeZoneConstants.pacificEaster());
        add(timeZoneConstants.atlanticSouthGeorgia());
        add(timeZoneConstants.africaElAaiun());
        add(timeZoneConstants.americaCampoGrande());
        add(timeZoneConstants.americaDawsonCreek());
        add(timeZoneConstants.antarcticaVostok());
        add(timeZoneConstants.europeBucharest());
        add(timeZoneConstants.americaPortoVelho());
        add(timeZoneConstants.europeMonaco());
        add(timeZoneConstants.americaIndianaMarengo());
        add(timeZoneConstants.africaCeuta());
        add(timeZoneConstants.americaWinnipeg());
        add(timeZoneConstants.asiaAqtobe());
        add(timeZoneConstants.africaDakar());
        add(timeZoneConstants.americaFortaleza());
        add(timeZoneConstants.pacificTarawa());
        add(timeZoneConstants.africaAddisAbaba());
        add(timeZoneConstants.pacificEfate());
        add(timeZoneConstants.pacificJohnston());
        add(timeZoneConstants.asiaQyzylorda());
        add(timeZoneConstants.asiaJerusalem());
        add(timeZoneConstants.pacificAuckland());
        add(timeZoneConstants.americaTortola());
        add(timeZoneConstants.americaDenver());
        add(timeZoneConstants.indianChagos());
        add(timeZoneConstants.americaGlaceBay());
        add(timeZoneConstants.africaGaborone());
        add(timeZoneConstants.africaTunis());
        add(timeZoneConstants.americaMontevideo());
        add(timeZoneConstants.asiaGaza());
        add(timeZoneConstants.europeDublin());
        add(timeZoneConstants.antarcticaDumontDUrville());
        add(timeZoneConstants.americaIndianaKnox());
        add(timeZoneConstants.asiaNovosibirsk());
        add(timeZoneConstants.africaKigali());
        add(timeZoneConstants.americaGrandTurk());
        add(timeZoneConstants.africaLagos());
        add(timeZoneConstants.europeSofia());
        add(timeZoneConstants.americaLima());
        add(timeZoneConstants.americaAnchorage());
        add(timeZoneConstants.pacificNauru());
    }

    private void add(String string) {

    	TimeZoneInfo tzi = TimeZoneInfo.buildTimeZoneData(string);
        TimeZone timeZone = TimeZone.createTimeZone(tzi);
        map.put(timeZone.getID(),new TimeZoneContainer( null, tzi, timeZone, string));
    }

    public Set<String> getAvailableIDs() {
        return map.keySet();
    }

    public TimeZone getZone(String id) {

        return map.get(id).getTimeZone();
    }

    public TimeZoneContainer getTimeZoneContainer(String id)
    {
    	return map.get(id);
    }

    public TimeZoneConstants getTimeZoneConstants(){
    	return timeZoneConstants;
    }
}
