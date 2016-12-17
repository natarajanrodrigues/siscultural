///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package io.github.siscultural.repositories;
//
//import io.github.siscultural.entities.Accomplishment;
//import io.github.siscultural.entities.Contract;
//import io.github.siscultural.model.AccomplishReport;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import java.util.List;
//
///**
// *
// * @author Natarajan Rodrigues
// */
//public interface AccomplishmentReportRepository extends JpaRepository<AccomplishReport, Long>{
//
////    @Query (value = "select new io.github.siscultural.model.AccomplishReport (p.name, count(a), sum(a.audience) )" +
////            "from contract c join program p on c.program_id = p.id join accomplishment a on a.contract_id = c.id " +
////            "group by p.name")
////    public List<AccomplishReport> accomplishmentReports();
//
//    @Query (value = "select new io.github.siscultural.model.AccomplishReport (p.name, count(a), sum(a.audience) )" +
////            "from Contract c join Program p on c.program_id = p.id join Accomplishment a on a.contract_id = c.id " +
////            "from Contract c join Program p join Accomplishment a  " +
////            "from Contract c join fetch c.program p join fetch a.contract a)  " +
//            "from Accomplishment a join fetch a.contract c join fetch c.program p  " +
//            "group by p.name")
//    public List<AccomplishReport> accomplishmentReports();
//}
