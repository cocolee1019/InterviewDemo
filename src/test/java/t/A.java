package t;

public class A {

    /**
     * @param aTime 开始时间
     * @param bTime 结束时间
     * @return
     */
    public static int distanceOfTimeSegment(String aTime, String bTime) {
        final String[] s1 = aTime.split(" ");
        int aWeek = Integer.parseInt(s1[0]);
        int aHH = Integer.parseInt(s1[1].split(":")[0]);
        int amm = Integer.parseInt(s1[1].split(":")[1]);
        int aSS = Integer.parseInt(s1[1].split(":")[2]);

        final String[] s2 = bTime.split(" ");
        int bWeek = Integer.parseInt(s2[0]);
        int bHH = Integer.parseInt(s2[1].split(":")[0]);
        int bmm = Integer.parseInt(s2[1].split(":")[1]);
        int bSS = Integer.parseInt(s2[1].split(":")[2]);

        //检测时段范围是否跨周
        if ((aWeek > bWeek) || (aWeek == bWeek && aHH > bHH) || (aWeek == bWeek && aHH == bHH && amm > bmm) || (aWeek == bWeek && aHH == bHH && amm == bmm && aSS > bSS)) {
            bWeek += 7;
        }

        return (((bWeek - aWeek) * 24 + bHH - aHH) * 60 + bmm - amm) * 60 + bSS - aSS;
    }

    public static void main(String[] args) {
        //差40秒
        System.out.println(distanceOfTimeSegment("1 19:50:46", "1 20:01:30"));

        //
        System.out.println(distanceOfTimeSegment("1 00:00:10", "1 00:01:40"));

        System.out.println(distanceOfTimeSegment("1 00:00:10", "1 00:00:10"));

        //差一天
        System.out.println(distanceOfTimeSegment("1 00:00:10", "2 00:00:10"));

        //跨周
        System.out.println(distanceOfTimeSegment("1 00:00:41", "1 00:00:40"));

        System.out.println(distanceOfTimeSegment("1 00:00:40", "1 00:00:39"));
    }
}
