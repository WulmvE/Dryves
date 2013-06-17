package session;

import entity.Admin;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author patrick
 */
@Stateless
public class AdminFacade extends AbstractFacade<Admin> {

    @PersistenceContext(unitName = "DryvesPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminFacade() {
        super(Admin.class);
    }    
    
    /**
     * Executes different type of statistical SQL Queries, based upon which type
     * is requested
     *
     * @param type
     * @return List<Object[]> 
     */
    public List<Object[]> getStats(String type, String from, String to, String by) {

        String query = "";
        String dateFormat = "%d/%m/%Y";

        //Several SQL queries. 'type' determines which one is chosen
        if (type.equals("topCreated")) {

            query = "SELECT M.alias, COUNT(*)AS aantal_ritten\n"
                    + "FROM ride R, member M\n"
                    + "WHERE R.idMember = M.idMember\n"
                    + "AND R.createdOn >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "AND R.createdOn <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "GROUP BY R.idmember\n"
                    + "ORDER BY aantal_ritten DESC LIMIT 5";
        }
        else if (type.equals("topDriven")) {

            query = "SELECT M.alias, COUNT(*) AS aantal_ritten\n"
                    + "FROM negotiation N, member M, ride R\n"
                    + "WHERE N.acceptedDriver= 1\n"
                    + "AND N.acceptedPassenger= 1\n"
                    + "AND N.idMember = M.idMember\n"
                    + "AND N.idRide = R.idRide\n"
                    + "AND R.departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "AND R.departureDate  <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "GROUP BY N.idMember\n"
                    + "ORDER BY aantal_ritten DESC LIMIT 5";
        }
        else if (type.equals("topRevenue")) {

            query = "SELECT M.`alias`, FORMAT(SUM(opr.oprb),2) AS opm\n"
                    + "FROM (SELECT date(departureDate) AS date, count(N.idMember) * R.`askingPrice`* P.`size` /100 AS oprb, R.`idMember`\n"
                    + "      FROM negotiation N, ride R, percentage P\n"
                    + "      WHERE acceptedDriver = 1\n"
                    + "      AND acceptedPassenger = 1\n"
                    + "      AND N.`idRide` = R.`idRide`\n"
                    + "      AND R.departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "      AND R. departureDate <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "      AND R.createdOn >= P.`dateFrom`\n"
                    + "      AND (R.createdOn < P.`dateTo` OR P.`dateTo` IS NULL)\n"
                    + "      GROUP BY R.`idRide`) AS opr, member M\n"
                    + "WHERE opr.idMember = M.`idMember`\n"
                    + "GROUP BY opr.idMember\n"
                    + "ORDER BY opm DESC LIMIT 5";
        }
        else if (type.equals("topDestination")) {
            query = "SELECT R.endLocation, COUNT(*) AS aantal_ritten\n"
                    + "FROM ride R\n"
                    + "WHERE R.departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "AND R.departureDate  <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "GROUP BY R.endLocation\n"
                    + "ORDER BY aantal_ritten DESC LIMIT 5";
        }
        else if (type.equals("topDeparture")) {
            query = "SELECT R.startLocation, COUNT(*) AS aantal_ritten\n"
                    + "FROM ride R\n"
                    + "WHERE R.departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "AND R.departureDate  <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "GROUP BY R.startLocation\n"
                    + "ORDER BY aantal_ritten DESC LIMIT 5";
        }
        else if (type.equals("totalRides")) {
            query = "SELECT q1.date,  (@runtot := @runtot + q1.count) AS running_total\n"
                    + "FROM (SELECT @runtot:=0," + by + "(departureDate) AS date, COUNT(*) AS count\n"
                    + "      FROM  ride\n"
                    + "      WHERE departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "      AND departureDate <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "      GROUP  BY date\n"
                    + "      ORDER  BY date(departureDate)) AS q1";
        }
        else if (type.equals("totalRidesNew")) {
            query = "SELECT " + by + "(departureDate) AS date, COUNT(*) AS count\n"
                    + "      FROM  ride\n"
                    + "      WHERE departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "      AND departureDate <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "      GROUP  BY date\n"
                    + "      ORDER  BY date(departureDate)";
        }
        else if (type.equals("totalMembers")) {
            query = "SELECT q1.date,  (@runtot := @runtot + q1.count) AS running_total\n"
                    + "FROM (SELECT @runtot:=0," + by + "(memberSince) AS date, COUNT(*) AS count\n"
                    + "      FROM  member\n"
                    + "      WHERE memberSince >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "      AND memberSince <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "      GROUP  BY date\n"
                    + "      ORDER  BY date(memberSince)) AS q1";
        }
        else if (type.equals("totalMembersNew")) {
            query = "SELECT " + by + "(memberSince) AS date, COUNT(*) AS count\n"
                    + "      FROM  member\n"
                    + "      WHERE memberSince >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "      AND memberSince <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "      GROUP  BY date\n"
                    + "      ORDER  BY date(memberSince)";
        }
        else if (type.equals("totalMilage")) {
            query = "SELECT q1.date,(@runtot := @runtot + q1.milage) AS running_total\n"
                    + "FROM (SELECT @runtot:=0, " + by + "(departureDate) AS date, ROUND(SUM(distance),2) AS milage\n"
                    + "      FROM  ride\n"
                    + "      WHERE departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "      AND departureDate <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "      GROUP  BY date\n"
                    + "      ORDER  BY (departureDate)) AS q1";
        }
        else if (type.equals("totalMilageNew")) {
            query = "SELECT " + by + "(departureDate) AS date, ROUND(SUM(distance),2) AS milage\n"
                    + "      FROM  ride\n"
                    + "      WHERE departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "      AND departureDate <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "      GROUP  BY date\n"
                    + "      ORDER  BY (departureDate)";
        }
        else if (type.equals("totalRevenue")) {
            query = "SELECT date, (@runtot := @runtot + opd.opdb) AS running_total\n"
                    + "FROM (SELECT @runtot:=0, date, SUM(opr.oprb) AS opdb, count(opr.oprb) AS opdc\n"
                    + "        FROM (SELECT " + by + "(departureDate) AS date, departureDate, COUNT(N.idMember) * R.`askingPrice`* P.`size` /100 AS oprb\n"
                    + "            FROM negotiation N, ride R, percentage P\n"
                    + "            WHERE acceptedDriver = 1\n"
                    + "            AND acceptedPassenger = 1\n"
                    + "            AND N.`idRide` = R.`idRide`\n"
                    + "            AND R.departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "            AND R. departureDate <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "            AND createdOn >= P.`dateFrom`\n"
                    + "            AND (createdOn < P.`dateTo` OR P.`dateTo` IS NULL)\n"
                    + "            GROUP BY R.`idRide` ) AS opr\n"
                    + "        GROUP BY date ORDER  BY (departureDate) ) AS opd";
        }
        else if (type.equals("totalRevenueNew")) {
            query = "SELECT date, SUM(opr.oprb) AS opdb\n"
                    + "        FROM (SELECT " + by + "(departureDate) AS date, departureDate, count(N.idMember) * R.`askingPrice`* P.`size` /100 AS oprb\n"
                    + "            FROM negotiation N, ride R, percentage P\n"
                    + "            WHERE acceptedDriver = 1\n"
                    + "            AND acceptedPassenger = 1\n"
                    + "            AND N.`idRide` = R.`idRide`\n"
                    + "            AND R.departureDate >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "            AND R. departureDate <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "            AND createdOn >= P.`dateFrom`\n"
                    + "            AND (createdOn < P.`dateTo` OR P.`dateTo` IS NULL)\n"
                    + "            GROUP BY R.`idRide`) AS opr\n"
                    + "        GROUP BY date ORDER  BY (departureDate)\n";
        }
        else if (type.equals("distGender")) {
            query = "SELECT \n"
                    + "CASE\n"
                    + "WHEN gender = 'm' THEN 'mannen'\n"
                    + "WHEN gender = 'v' THEN 'vrouwen'\n"
                    + "END AS gf, COUNT(gender),CONCAT(ROUND(COUNT(gender)/tm.c*100,2), '%') AS perc\n"
                    + "FROM member,(SELECT COUNT(*) AS c FROM member ) AS tm\n"
                    + "WHERE memberSince>= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "AND memberSince <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "GROUP BY gender";
        }
        else if (type.equals("distAge")) {
            query = "SELECT CASE\n"
                    + "  WHEN TIMESTAMPDIFF(YEAR,birthDate,CURDATE()) BETWEEN 18 AND 25 THEN '18-25' \n"
                    + "  WHEN TIMESTAMPDIFF(YEAR,birthDate,CURDATE()) BETWEEN  25  AND 35 THEN '25-35'\n"
                    + "  WHEN TIMESTAMPDIFF(YEAR,birthDate,CURDATE()) BETWEEN  35  AND 45 THEN '35-45'\n"
                    + "  WHEN TIMESTAMPDIFF(YEAR,birthDate,CURDATE()) BETWEEN  45  AND 55 THEN '45-55'\n"
                    + "  WHEN TIMESTAMPDIFF(YEAR,birthDate,CURDATE()) BETWEEN  55  AND 65 THEN '55-65'\n"
                    + "  WHEN TIMESTAMPDIFF(YEAR,birthDate,CURDATE()) BETWEEN  65  AND 75 THEN '65-75' \n"
                    + "  ELSE '75+' \n"
                    + "  END AS age_range, count(*), CONCAT(ROUND(COUNT(*)/tm.c*100,2), \"%\") AS perc\n"
                    + "FROM member M, (SELECT COUNT(*) AS c FROM member ) AS tm\n"
                    + "WHERE M.`memberSince` >= STR_TO_DATE('" + from + "', '" + dateFormat + "')\n"
                    + "AND M.`memberSince` <= STR_TO_DATE('" + to + "', '" + dateFormat + "')\n"
                    + "GROUP BY age_range";
        }
        
        //Entity manager executes the chosen query. Result are stored in a List and returned
        Query q = em.createNativeQuery(query);
        List<Object[]> stats = q.getResultList();
        return stats;
    }
}
