-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 02, 2018 at 10:46 AM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.23

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hms`
--

-- --------------------------------------------------------

--
-- Table structure for table `appointment`
--

CREATE TABLE `appointment` (
  `apRefNo` varchar(10) NOT NULL,
  `date` date NOT NULL,
  `time` varchar(100) DEFAULT NULL,
  `appNo` int(11) NOT NULL,
  `sessionID` varchar(10) NOT NULL,
  `patientID` varchar(10) NOT NULL,
  `fee` float NOT NULL,
  `source` varchar(20) NOT NULL,
  `billStatus` varchar(20) NOT NULL DEFAULT 'UnBilled'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appointment`
--

INSERT INTO `appointment` (`apRefNo`, `date`, `time`, `appNo`, `sessionID`, `patientID`, `fee`, `source`, `billStatus`) VALUES
('AP014', '2017-11-19', '07:00:00', 1, '35', '6', 1900, 'Phone', 'UnBilled'),
('AP016', '2017-11-25', '08:00:00', 2, '62', '1', 1400, 'Walkin', 'UnBilled'),
('AP017', '2017-11-25', '08:04:00', 3, '62', '1', 1400, 'Walkin', 'UnBilled'),
('AP019', '2017-12-10', '07:00:00', 1, '70', '8', 1900, 'Walkin', 'UnBilled'),
('AP077', '2017-10-08', '16:00', 1, '67', '6', 1900, 'Walkin', 'Billed'),
('AP078', '2017-10-08', '16:12', 2, '67', '5', 1900, 'Walkin', 'Billed'),
('AP079', '2017-10-08', '16:24', 3, '67', '6', 1900, 'Walkin', 'Billed'),
('AP080', '2017-10-08', '16:36', 4, '67', '6', 1900, 'Walkin', 'Billed'),
('AP081', '2017-10-08', '16:48', 5, '67', '1', 1900, 'Walkin', 'Billed'),
('AP082', '2017-10-18', '07:00', 1, '68', '1', 1900, 'Walkin', 'Billed'),
('AP083', '2017-10-18', '07:10', 2, '68', '1', 1900, 'Walkin', 'Billed');

-- --------------------------------------------------------

--
-- Table structure for table `appseq`
--

CREATE TABLE `appseq` (
  `id` int(11) NOT NULL,
  `name` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `appseq`
--

INSERT INTO `appseq` (`id`, `name`) VALUES
(1, 'aaaaa'),
(2, 'aaaaa'),
(3, 'aaaaa'),
(4, 'aaaaa'),
(5, 'aaaaa'),
(6, 'aaaaa'),
(7, 'aaaaa'),
(8, 'aaaaa'),
(9, 'aaaaa'),
(10, 'aaaaa'),
(11, 'aaaaa'),
(12, 'aaaaa'),
(13, 'aaaaa'),
(14, 'aaaaa'),
(15, 'aaaaa'),
(16, 'aaaaa'),
(17, 'aaaaa'),
(18, 'aaaaa'),
(19, 'aaaaa'),
(20, 'aaaaa');

-- --------------------------------------------------------

--
-- Table structure for table `assests`
--

CREATE TABLE `assests` (
  `serialNo` varchar(20) NOT NULL,
  `type` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `lifeExp` int(11) NOT NULL,
  `description` varchar(100) NOT NULL,
  `manufacturer` varchar(50) NOT NULL,
  `supplier` varchar(50) NOT NULL,
  `purchDate` date NOT NULL,
  `warExpDate` date NOT NULL,
  `price` double NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `assests`
--

INSERT INTO `assests` (`serialNo`, `type`, `name`, `lifeExp`, `description`, `manufacturer`, `supplier`, `purchDate`, `warExpDate`, `price`, `qty`) VALUES
('DE35G3D45D', 'Laboratory Equipments', 'microscope', 10, 'good in functionality', 'Greenware', 'SACHINI', '2017-11-01', '2017-11-24', 25000, 2),
('DG53FSFS34', 'Laboratory Equipments', 'refridgerator', 15, 'very good', 'singer', 'SHALINDRI', '2017-11-04', '2017-11-24', 45000, 2),
('GUE24J64D9', 'Laboratory Equipments', 'Pipet', 12, 'in good condition', 'ABC', ' AYODYA', '2017-11-02', '2017-11-24', 2500, 10),
('GUG7985HUY', 'Laboratory Equipments', 'wre', 43, 'eftwegte', 'wfweg', 'TIFFANY', '2017-11-10', '2017-11-17', 46464, 2),
('Q34TG654D3', 'Diagnostics', 'stethoscope', 5, 'small, easy to use', 'Abans ', ' AYODYA', '2017-11-02', '2017-11-22', 5000, 12);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billNo` varchar(10) NOT NULL,
  `patientID` varchar(10) NOT NULL,
  `totAmount` double NOT NULL,
  `descr` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bloodresults`
--

CREATE TABLE `bloodresults` (
  `brID` int(10) NOT NULL,
  `testNo` varchar(10) DEFAULT NULL,
  `glucoseF` double DEFAULT NULL,
  `tsHormone` double DEFAULT NULL,
  `cholesterol` double DEFAULT NULL,
  `triglyceride` double DEFAULT NULL,
  `hdl` double DEFAULT NULL,
  `ldl` double DEFAULT NULL,
  `vdl` double DEFAULT NULL,
  `ratio` double DEFAULT NULL,
  `bUrea` double DEFAULT NULL,
  `ppPlasmaGlu` double DEFAULT NULL,
  `neutro` double DEFAULT NULL,
  `lympho` double DEFAULT NULL,
  `eosin` double DEFAULT NULL,
  `mono` double DEFAULT NULL,
  `basophil` double DEFAULT NULL,
  `hb` double DEFAULT NULL,
  `pcv` double DEFAULT NULL,
  `pateletC` double DEFAULT NULL,
  `wbwCount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bloodresults`
--

INSERT INTO `bloodresults` (`brID`, `testNo`, `glucoseF`, `tsHormone`, `cholesterol`, `triglyceride`, `hdl`, `ldl`, `vdl`, `ratio`, `bUrea`, `ppPlasmaGlu`, `neutro`, `lympho`, `eosin`, `mono`, `basophil`, `hb`, `pcv`, `pateletC`, `wbwCount`) VALUES
(1, '2', 77, 44, 2, 3, 9, 99, 7, 4, 44, 2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '5', 255, 45, 12, 454, 7, 78, 46, 31, 78, 124, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, '4', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 130, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, '3', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 60, 70, 10, 15, 25, 50, 22, 90, 200),
(5, '', NULL, NULL, 250, 122, 45, 125, 152, 4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `buy`
--

CREATE TABLE `buy` (
  `drugCode` varchar(10) NOT NULL,
  `patientID` varchar(10) NOT NULL,
  `prescriptionNo` varchar(10) NOT NULL,
  `qty` int(11) NOT NULL,
  `noOfDays` int(11) NOT NULL,
  `mQty` int(11) NOT NULL,
  `aQty` int(11) NOT NULL,
  `nQty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `buy`
--

INSERT INTO `buy` (`drugCode`, `patientID`, `prescriptionNo`, `qty`, `noOfDays`, `mQty`, `aQty`, `nQty`) VALUES
('', '5', '7', 0, 0, 3, 0, 0),
('DR00020', 'PT00001', 'p003', 56, 0, 0, 0, 0),
('DR00021', 'PT00001', 'PR65', 10, 0, 0, 0, 0),
('DR00022', '1', '0', 12, 3, 2, 0, 2),
('DR00022', '1', 'P001', 15, 0, 0, 0, 0),
('DR00022', '2', 'P002', 20, 0, 0, 0, 0),
('DR00022', '4', '0', 29360, 367, 70, 0, 10),
('DR00022', 'P001', '1', 56, 0, 0, 0, 0),
('DR00022', 'P003', 'PR56', 12, 0, 0, 0, 0),
('DR00040', '1', '1', 20, 4, 1, 1, 1),
('DR00040', '2', 'P002', 40, 0, 0, 0, 0),
('DR00040', '3', '4', 10, 2, 2, 0, 3),
('DR00040', 'P0025', 'PR56', 5, 4, 1, 1, 1),
('DR00042', '2', 'P002', 25, 0, 0, 0, 0),
('DR00075', '4', '0', 6477, 381, 0, 17, 0),
('DR00086', '1', '0', 12, 3, 2, 0, 2),
('DR00091', '3', '4', 10, 2, 2, 0, 3),
('DR00092', '4', '0', 29360, 367, 70, 0, 10),
('dr001', 'rh', 'p001', 45, 0, 0, 0, 0),
('DR00102', '1', '6', 6, 3, 0, 0, 2),
('DR00104', '4', '5', 15, 3, 3, 0, 2),
('DR00106', '1', '6', 6, 3, 0, 0, 2),
('DR00106', '4', '5', 15, 3, 3, 0, 2),
('DR00107', '1', '3', 15, 5, 1, 1, 1),
('DR00107', '4', '5', 15, 3, 3, 0, 2),
('DR00108', '1', '6', 6, 3, 0, 0, 2);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `empNo` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `addr` varchar(100) NOT NULL,
  `Dob` date NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Date_of_Join` date NOT NULL,
  `ContactNo` varchar(11) NOT NULL,
  `RegNo` varchar(10) NOT NULL,
  `Speciality` varchar(50) NOT NULL,
  `Qualifcations` varchar(100) NOT NULL,
  `doctorFee` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`empNo`, `name`, `addr`, `Dob`, `Gender`, `Date_of_Join`, `ContactNo`, `RegNo`, `Speciality`, `Qualifcations`, `doctorFee`) VALUES
('EMP004', 'Prasad Katulanda', 'No 9, Fonseka Place , Colombo 4', '1962-09-07', 'male', '2017-09-08', '0774521123', '10233', 'Endocrinologist', 'MBBS (Colombo), Mphil(Oxon)', 1500),
('EMP006', 'Rajitha Suwaris', 'No 12, Colombo', '1985-09-08', 'male', '2017-09-04', '0771452236', '10253', 'None', 'MBBS(Colombo)', 1500),
('EMP009', 'Sachini Epa', 'Kaluthara', '1996-01-23', 'female', '2017-11-18', '0773575473', '64657', 'Psychologist', 'MBBS', 1200),
('EMP012', 'Ayodhya Dayananda', 'Gamapaha', '1996-11-06', 'male', '2017-11-06', '0174569982', '45221', 'Dermatologist', 'MBBS (UK)', 1000),
('EMP015', 'Kasun Mendis', 'Dehiwala', '1976-11-07', 'male', '2017-11-01', '0177725349', '10050', 'Endocrinologist', 'MBBS(Colombo)', 1500),
('EMP017', ' Saman Perera', 'Dehiwala', '1976-11-07', 'male', '2017-11-01', '0177725349', '10541', 'Endocrinologist', 'MBBS(Colombo)', 1500),
('EMP020', 'Sunil Mendis', 'Dehiwala', '1974-11-01', 'male', '2017-11-06', '0177725349', '94565', 'Physician', 'MBBS(Colombo)', 1500);

-- --------------------------------------------------------

--
-- Table structure for table `donaequip`
--

CREATE TABLE `donaequip` (
  `donNo` int(10) NOT NULL,
  `serialNo` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `lifeExpect` int(10) NOT NULL,
  `description` varchar(100) NOT NULL,
  `manuf` varchar(50) NOT NULL,
  `purDate` date NOT NULL,
  `donDate` date NOT NULL,
  `price` date NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `drugs`
--

CREATE TABLE `drugs` (
  `drugCode` varchar(10) NOT NULL,
  `chName` varchar(30) NOT NULL,
  `drugName` varchar(50) NOT NULL,
  `type` varchar(20) NOT NULL,
  `totQty` int(11) NOT NULL,
  `manufact` varchar(50) NOT NULL,
  `unitPrice` double NOT NULL,
  `mrgLvl` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drugs`
--

INSERT INTO `drugs` (`drugCode`, `chName`, `drugName`, `type`, `totQty`, `manufact`, `unitPrice`, `mrgLvl`) VALUES
('DR00102', 'Tetracycline', 'Duramycin 100', 'Liquid', 17, 'Astron Ltd', 10, 100),
('DR00103', 'Paracetamol', 'Calpol 50', 'Tablet', 36, 'Smith Ltd', 12.5, 150),
('DR00104', 'Gentamycin', 'Garamycin 50', 'Capsule', 410, 'Astron Ltd', 5, 145),
('DR00105', 'Metronidazole', 'Flagyl 250', 'Liquid', 56, 'Astron Ltd', 212, 100),
('DR00106', 'Cyclosporine', 'Atopica 100', 'Tablet', 290, 'Astron Ltd', 2, 150),
('DR00107', 'Tetracycline', 'Aureomycin 100', 'Liquid', 25, 'Smith Ltd', 856, 50),
('DR00108', 'Tetracycline', 'Minocin 250', 'Liquid', 17, 'Astron Ltd', 152, 50),
('DR00109', 'Paracetamol', 'Panadol 50', 'Tablet', 180, 'Astron Ltd', 2, 500),
('DR00110', 'Tetracycline', 'Hostacycline 100', 'Capsule', 20, 'Astron Ltd', 12, 100),
('DR00113', 'Metronidazole', 'Monistat 200', 'Tablet', 36, 'Smith Ltd', 10, 100),
('DR00114', 'Paracetamol', 'Tm 50', 'Tablet', 30, 'Astron Ltd', 23, 120),
('DR00117', 'Gentak ', 'Gentamycin 10', 'Liquid', 56, 'Smith Ltd', 250, 100),
('DR00118', 'Paracetamol', 'Tylenol 10', 'Tablet', 56, 'Smith Ltd', 15, 200),
('DR00120', 'Gentamycin', 'Gentamicina 100', 'Liquid', 10, 'Astron Ltd', 652.32, 100),
('DR00121', 'Tetracycline', 'Duramycin 100', 'Liquid', 0, 'Astron Ltd', 10, 100),
('DR00122', 'Cyclosporine', 'Atopica 100', 'Tablet', 0, 'Astron Ltd', 2, 150),
('DR00123', 'Metronidazole', 'Flagyl 250', 'Liquid', 0, 'Astron Ltd', 212, 100),
('DR00124', 'Cyclosporine', 'Neoral', 'Capsule', 45, 'Astron Ltd', 52, 100),
('DR00125', 'Cyclosporine', 'Neoral', 'Capsule', 0, 'Astron Ltd', 52, 100);

-- --------------------------------------------------------

--
-- Table structure for table `drug_seq`
--

CREATE TABLE `drug_seq` (
  `id` int(11) NOT NULL,
  `name` varchar(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `drug_seq`
--

INSERT INTO `drug_seq` (`id`, `name`) VALUES
(1, 'aaaa'),
(2, 'aaa'),
(3, 'aaa'),
(4, 'aaa'),
(5, 'aaa'),
(6, 'aaa'),
(7, 'aaa'),
(8, 'aaa'),
(9, 'aaa'),
(10, 'aaa'),
(11, 'aaa'),
(12, 'aaa'),
(13, 'aaa'),
(14, 'aaa'),
(15, 'aaa'),
(16, 'aaa'),
(17, 'aaa'),
(18, 'aaa'),
(19, 'aaa'),
(20, 'aaa'),
(21, 'aaa'),
(22, 'aaa'),
(23, 'aaa'),
(24, 'aaa'),
(25, 'aaa'),
(26, 'aaa'),
(27, 'aaa'),
(28, 'aaa'),
(29, 'aaa'),
(30, 'aaa'),
(31, 'aaa'),
(32, 'aaa'),
(33, 'aaa'),
(34, 'aaa'),
(35, 'aaa'),
(36, 'aaa'),
(37, 'aaa'),
(38, 'aaa'),
(39, 'aaa'),
(40, 'aaa'),
(41, 'aaa'),
(42, 'aaa'),
(43, 'aaa'),
(44, 'aaa'),
(45, 'aaa'),
(46, 'aaa'),
(47, 'aaa'),
(48, 'aaa'),
(49, 'aaa'),
(50, 'aaa'),
(51, 'aaa'),
(52, 'aaa'),
(53, 'aaa'),
(54, 'aaa'),
(55, 'aaa'),
(56, 'aaa'),
(57, 'aaa'),
(58, 'aaa'),
(59, 'aaa'),
(60, 'aaa'),
(61, 'aaa'),
(62, 'aaa'),
(63, 'aaa'),
(64, 'aaa'),
(65, 'aaa'),
(66, 'aaa'),
(67, 'aaa'),
(68, 'aaa'),
(69, 'aaa'),
(70, 'aaa'),
(71, 'aaa'),
(72, 'aaa'),
(73, 'aaa'),
(74, 'aaa'),
(75, 'aaa'),
(76, 'aaa'),
(77, 'aaa'),
(78, 'aaa'),
(79, 'aaa'),
(80, 'aaa'),
(81, 'aaa'),
(82, 'aaa'),
(83, 'aaa'),
(84, 'aaa'),
(85, 'aaa'),
(86, 'aaa'),
(87, 'aaa'),
(88, 'aaa'),
(89, 'aaa'),
(90, 'aaa'),
(91, 'aaa'),
(92, 'aaa'),
(93, 'aaa'),
(94, 'aaa'),
(95, 'aaa'),
(96, 'aaa'),
(97, 'aaa'),
(98, 'aaa'),
(99, 'aaa'),
(100, 'aaa'),
(101, 'aaa'),
(102, 'aaa'),
(103, 'aaa'),
(104, 'aaa'),
(105, 'aaa'),
(106, 'aaa'),
(107, 'aaa'),
(108, 'aaa'),
(109, 'aaa'),
(110, 'aaa'),
(111, 'aaa'),
(112, 'aaa'),
(113, 'aaa'),
(114, 'aaa'),
(115, 'aaa'),
(116, 'aaa'),
(117, 'aaa'),
(118, 'aaa'),
(119, 'aaa'),
(120, 'aaa'),
(121, 'aaa'),
(122, 'aaa'),
(123, 'aaa'),
(124, 'aaa'),
(125, 'aaa');

-- --------------------------------------------------------

--
-- Table structure for table `emp_seq`
--

CREATE TABLE `emp_seq` (
  `ID` int(10) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emp_seq`
--

INSERT INTO `emp_seq` (`ID`, `name`) VALUES
(1, 'aaaaa'),
(2, 'aaaaa'),
(3, 'aaaaa'),
(4, 'aaaaa'),
(5, 'aaaaa'),
(6, 'aaaaa'),
(7, 'aaaaa'),
(8, 'aaaaa'),
(9, 'aaaaa'),
(10, 'aaaaa'),
(11, 'aaaaa'),
(12, 'aaaaa'),
(13, 'aaaaa'),
(14, 'aaaaa'),
(15, 'aaaaa'),
(16, 'aaaaa'),
(17, 'aaaaa'),
(18, 'aaaaa'),
(19, 'aaaaa'),
(20, 'aaaaa');

-- --------------------------------------------------------

--
-- Table structure for table `eqorder`
--

CREATE TABLE `eqorder` (
  `supid` varchar(5) NOT NULL,
  `serialNo` varchar(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `datesup` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eqorder`
--

INSERT INTO `eqorder` (`supid`, `serialNo`, `quantity`, `datesup`) VALUES
('', 'aa11122', 32, '2017-11-15'),
('', 'asd123', 12, '2017-11-04'),
('', 'jinki123', 12, '2017-11-11'),
('s123', 'asd123', 12, '2017-11-03'),
('s123', 'asd123', 12, '2017-11-03'),
('s123', 'asd123', 12, '2017-11-03'),
('s123', 'qwe123', 1, '2017-11-03'),
('S003', 'DG53FSFS34', 10, '2017-11-10'),
('S003', 'E57G6AS89A', 2, '2017-11-10'),
('S004', 'DE35G3D45D', 1, '2017-11-03'),
('S004', 'DE35G3D45D', 1, '2017-11-16'),
('S003', 'DE35G3D45D', 3, '2017-11-09');

-- --------------------------------------------------------

--
-- Table structure for table `eqsupplier`
--

CREATE TABLE `eqsupplier` (
  `supid` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `address` varchar(100) NOT NULL,
  `telno` int(15) NOT NULL,
  `email` varchar(75) NOT NULL,
  `DOJ` date NOT NULL,
  `remarks` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `eqsupplier`
--

INSERT INTO `eqsupplier` (`supid`, `name`, `address`, `telno`, `email`, `DOJ`, `remarks`) VALUES
('QQQQ', 'wwww', 'rgrrew', 24444443, 'ayodhya.dayananda@gmail.com', '2017-11-24', 'sfsf'),
('S001', ' AYODYA', 'GAMPAHA', 712801032, 'ayodya.dayananda@gmail.com', '2017-11-04', 'excellent'),
('S002', 'SHALINDRI', 'KANDANA', 719531610, 'shalindri.20@gmail.com', '2017-11-18', 'very good'),
('S004', 'SACHINI', 'KALUTHARA', 777342508, 'sachi.epa96@gmail.com ', '2017-11-17', 'good'),
('S005', 'STEPHINI ', 'KALUTARA', 716452389, 'minolispencer@gmail.com', '2017-11-12', 'very good');

-- --------------------------------------------------------

--
-- Table structure for table `equiptypename`
--

CREATE TABLE `equiptypename` (
  `no` int(11) NOT NULL,
  `type` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `issued`
--

CREATE TABLE `issued` (
  `issueNo` int(11) NOT NULL,
  `presNo` varchar(10) NOT NULL,
  `drugCode` varchar(10) NOT NULL,
  `qty` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `issued`
--

INSERT INTO `issued` (`issueNo`, `presNo`, `drugCode`, `qty`) VALUES
(1, 'PR65', 'DR00021', 8),
(2, 'PR65', 'DR00019', 59),
(3, 'p001', 'dr001', 49),
(4, 'PR65', 'DR00019', 58),
(5, 'PR65', 'DR00019', 56),
(6, 'PR65', 'DR00021', 10),
(7, 'p003', 'DR00020', 3),
(8, 'p003', 'DR00020', 56),
(9, 'PR65', 'DR00019', 56),
(10, 'PR65', 'DR00021', 14),
(11, 'PR65', 'DR00019', 56),
(12, 'PR56', 'DR00022', 13),
(13, 'PR56', 'DR00022', 20),
(14, 'PR56', 'DR00022', 20),
(15, 'PR56', 'DR00022', 20),
(16, 'PR56', 'DR00022', 20),
(17, 'PR56', 'DR00022', 20),
(18, 'PR56', 'DR00022', 30),
(19, 'PR56', 'DR00022', 25),
(20, 'PR56', 'DR00022', 25),
(21, 'PR56', 'DR00022', 15),
(22, 'PR56', 'DR00022', 15),
(23, 'PR56', 'DR00040', 5),
(24, 'PR56', 'DR00022', 22),
(25, 'PR56', 'DR00022', 501),
(26, 'PR56', 'DR00022', 5),
(27, 'PR56', 'DR00022', 65),
(28, 'PR56', 'DR00022', 120),
(29, 'PR56', 'DR00022', 501),
(30, 'PR56', 'DR00040', 5),
(31, 'PR56', 'DR00022', 100),
(32, 'PR56', 'DR00022', 10),
(33, 'PR56', 'DR00022', 10),
(34, '1', 'DR00040', 25),
(35, '1', 'DR00040', 20),
(36, '6', 'DR00106', 6),
(37, '6', 'DR00108', 32),
(38, '5', 'DR00107', 15),
(39, '5', 'DR00107', 15);

-- --------------------------------------------------------

--
-- Table structure for table `labreports`
--

CREATE TABLE `labreports` (
  `labTestNo` varchar(10) NOT NULL,
  `patientID` varchar(10) NOT NULL,
  `type` varchar(10) NOT NULL,
  `charge` double NOT NULL,
  `testCon` varchar(50) NOT NULL,
  `sampleTkn` varchar(50) NOT NULL,
  `test_result` varchar(500) NOT NULL,
  `notes` varchar(200) NOT NULL,
  `date` date NOT NULL,
  `testRefNo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `log`
--

CREATE TABLE `log` (
  `userName` varchar(25) NOT NULL,
  `login` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `logout` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `log`
--

INSERT INTO `log` (`userName`, `login`, `logout`) VALUES
('Admin1', '2017-11-18 03:09:32', '2017-11-18 03:10:07'),
('Admin1', '2017-11-18 06:26:00', '2017-11-18 06:26:57'),
('admin1', '2017-11-18 07:07:22', '2017-11-18 07:07:47'),
('admin1', '2017-11-18 07:35:16', '2017-11-18 07:37:46'),
('admin1', '2017-11-18 07:48:01', '2017-11-18 07:52:17'),
('admin1', '2017-11-18 07:53:53', '2017-11-18 07:55:13'),
('Admin1', '2017-11-18 09:36:23', '2017-11-18 09:36:58'),
('Admin1', '2017-11-18 09:47:28', '2017-11-18 09:50:10'),
('Admin1', '2017-11-18 09:53:09', '2017-11-18 09:53:36'),
('Admin1', '2017-11-18 09:55:49', '2017-11-18 09:56:14'),
('Admin1', '2017-11-18 09:57:49', '2017-11-18 09:58:23'),
('Admin1', '2017-11-18 09:59:45', '2017-11-18 10:36:51'),
('Admin1', '2017-11-18 10:57:12', '2017-11-18 11:21:47'),
('Admin1', '2017-11-18 11:19:11', '2017-11-18 11:21:42'),
('Admin1', '2017-11-18 11:25:08', '2017-11-18 11:26:38'),
('Admin1', '2017-11-18 11:34:02', '2017-11-18 11:35:16'),
('Admin1', '2017-11-18 11:36:16', '2017-11-18 11:37:22'),
('Admin1', '2017-11-18 11:39:44', '2017-11-18 11:40:43'),
('Admin1', '2017-11-18 11:44:59', '2017-11-18 11:45:33'),
('Admin1', '2017-11-18 11:47:30', '2017-11-18 11:48:44'),
('Admin1', '2017-11-18 11:52:04', '2017-11-18 11:52:52'),
('Admin1', '2017-11-18 15:34:33', '2017-11-18 15:36:23'),
('Admin1', '2017-11-18 15:36:49', '2017-11-18 15:38:10'),
('Admin1', '2017-11-18 15:40:51', '2017-11-18 15:41:49'),
('Admin1', '2017-11-18 15:46:00', '2017-11-18 15:46:42'),
('Admin1', '2017-11-18 15:54:50', '2017-11-18 15:59:59'),
('Admin1', '2017-11-18 17:02:08', '2017-11-18 17:02:55'),
('admin1', '2017-11-18 17:03:55', '2017-11-18 17:04:23'),
('Admin1', '2017-11-18 17:06:25', '2017-11-18 17:06:59'),
('Admin1', '2017-11-18 17:11:45', '2017-11-18 17:12:32'),
('Admin1', '2017-11-18 17:19:03', '2017-11-18 17:20:11'),
('Admin1', '2017-11-18 17:28:59', '2017-11-18 17:30:13'),
('Admin1', '2017-11-18 22:41:09', '2017-11-18 22:43:05'),
('Admin1', '2017-11-18 22:49:11', '2017-11-18 22:49:52'),
('Admin1', '2017-11-18 22:56:27', '2017-11-18 22:57:01'),
('Admin1', '2017-11-18 22:58:59', '2017-11-18 22:59:19'),
('Admin1', '2017-11-18 23:09:44', '2017-11-18 23:11:21'),
('Admin1', '2017-11-18 23:16:17', '2017-11-18 23:18:42'),
('Admin1', '2017-11-18 23:24:30', '2017-11-18 23:25:24'),
('Admin1', '2017-11-18 23:26:07', '2017-11-18 23:27:17'),
('Admin1', '2017-11-18 23:39:58', '2017-11-18 23:40:42'),
('Admin1', '2017-11-18 23:43:43', '2017-11-18 23:44:01'),
('Admin1', '2017-11-18 23:56:45', '2017-11-18 23:57:30'),
('Admin1', '2017-11-19 00:04:42', '2017-11-19 00:04:52'),
('Admin1', '2017-11-19 00:09:51', '2017-11-19 00:10:04'),
('Admin1', '2017-11-19 00:11:45', '2017-11-19 00:12:16'),
('admin1', '2017-11-19 04:09:19', '2017-11-19 04:10:48'),
('admin1', '2017-11-19 04:12:17', '2017-11-19 04:13:32'),
('admin1', '2017-11-19 04:29:58', '2017-11-19 04:34:37'),
('admin1', '2017-11-19 04:43:01', '2017-11-19 04:44:09'),
('admin1', '2017-11-19 05:12:01', '2017-11-19 05:14:52'),
('admin1', '2017-11-19 05:19:27', '2017-11-19 05:19:48'),
('admin1', '2017-11-19 05:24:13', '2017-11-19 05:30:03'),
('admin1', '2017-11-19 05:39:01', '2017-11-19 05:43:13'),
('admin1', '2017-11-19 05:45:42', '2017-11-19 05:45:59'),
('admin1', '2017-11-19 05:50:23', '2017-11-19 05:51:30'),
('admin1', '2017-11-19 05:52:33', '2017-11-19 05:52:50'),
('admin1', '2017-11-19 05:54:12', '2017-11-19 05:55:48'),
('admin1', '2017-11-19 06:02:01', '2017-11-19 06:02:15'),
('admin1', '2017-11-19 06:04:51', '2017-11-19 06:05:21'),
('admin1', '2017-11-19 06:06:55', '2017-11-19 06:07:24'),
('admin1', '2017-11-19 06:59:06', '2017-11-19 06:59:30'),
('admin1', '2017-11-19 07:11:49', '2017-11-19 07:12:13'),
('admin1', '2017-11-19 07:21:08', '2017-11-19 07:21:43'),
('Admin1', '2017-11-19 07:37:16', '2017-11-19 07:37:49'),
('Admin1', '2017-11-19 07:39:52', '2017-11-19 07:40:34'),
('Admin1', '2017-11-19 08:15:46', '2017-11-19 08:16:08'),
('Admin1', '2017-11-19 08:17:47', '2017-11-19 08:18:20'),
('Admin1', '2017-11-19 08:20:25', '2017-11-19 08:20:54'),
('Admin1', '2017-11-19 09:19:30', '2017-11-19 09:20:38'),
('Admin1', '2017-11-19 09:22:05', '2017-11-19 09:22:52'),
('Admin1', '2017-11-19 09:25:07', '2017-11-19 09:25:34'),
('Admin1', '2017-11-19 09:27:21', '2017-11-19 09:27:57'),
('Admin1', '2017-11-19 09:30:01', '2017-11-19 09:31:25'),
('Admin1', '2017-11-19 09:32:46', '2017-11-19 09:33:43'),
('Admin1', '2017-11-19 09:45:43', '2017-11-19 09:46:04'),
('Admin1', '2017-11-19 09:50:11', '2017-11-19 09:50:58'),
('Admin1', '2017-11-19 09:52:10', '2017-11-19 09:52:21'),
('Admin1', '2017-11-19 09:54:31', '2017-11-19 09:55:36'),
('Admin1', '2017-11-19 10:03:24', '2017-11-19 10:07:50'),
('Admin1', '2017-11-19 10:26:03', '2017-11-19 10:29:59'),
('Admin1', '2017-11-19 10:38:44', '2017-11-19 10:38:59'),
('Admin1', '2017-11-19 10:53:12', '2017-11-19 11:14:34'),
('admin1', '2017-11-19 11:18:10', '2017-11-19 11:18:51'),
('admin1', '2017-11-19 11:21:12', '2017-11-19 11:21:49'),
('admin1', '2017-11-19 11:24:07', '2017-11-19 11:25:13'),
('admin1', '2017-11-19 11:28:40', '2017-11-19 11:43:42'),
('Admin1', '2017-11-19 13:53:26', '2017-11-19 13:58:10'),
('Admin1', '2017-11-19 14:27:42', '2017-11-19 14:28:24'),
('Admin1', '2017-11-19 14:51:48', '2017-11-19 14:53:25'),
('Admin1', '2017-11-19 14:54:40', '2017-11-19 14:54:54'),
('Admin1', '2017-11-19 14:55:36', '2017-11-19 14:55:50'),
('Admin1', '2017-11-19 14:57:24', '2017-11-19 15:00:33'),
('Admin1', '2017-11-19 15:05:51', '2017-11-19 15:06:13'),
('Admin1', '2017-11-19 15:08:47', '2017-11-19 15:10:04'),
('Admin1', '2017-11-19 15:17:02', '2017-11-19 15:19:44'),
('Admin1', '2017-11-19 15:23:06', '2017-11-19 15:23:27'),
('Admin1', '2017-11-19 15:27:33', '2017-11-19 15:28:14'),
('Admin1', '2017-11-19 15:31:49', '2017-11-19 15:41:41'),
('admin1', '2017-11-19 15:49:20', '2017-11-19 15:56:45'),
('Admin1', '2017-11-19 16:40:23', '2017-11-19 16:41:10'),
('Admin1', '2017-11-19 17:01:43', '2017-11-19 17:03:52'),
('Admin1', '2017-11-19 18:47:54', '2017-11-19 18:56:00'),
('Admin1', '2017-11-19 21:32:16', '2017-11-19 21:34:22'),
('Admin1', '2017-11-19 21:36:58', '2017-11-19 21:46:27'),
('Admin1', '2017-11-19 21:54:42', '2017-11-19 21:56:31'),
('Admin1', '2017-11-19 21:58:26', '2017-11-19 22:22:43'),
('Admin1', '2017-11-19 22:27:00', '2017-11-19 22:27:33'),
('Admin1', '2017-11-19 22:28:20', '2017-11-19 22:30:57'),
('Admin1', '2017-11-19 22:32:56', '2017-11-19 22:35:41'),
('Admin1', '2017-11-19 22:37:48', '2017-11-19 22:48:42'),
('Admin1', '2017-11-19 22:52:05', '2017-11-19 23:56:52'),
('Admin1', '2017-11-19 23:07:25', '2017-11-19 23:07:49'),
('Admin1', '2017-11-19 23:20:44', '2017-11-19 23:21:28'),
('Admin1', '2017-11-20 00:07:32', '2017-11-20 00:08:11'),
('Admin1', '2017-11-20 00:09:53', '2017-11-20 00:11:11'),
('admin1', '2017-11-20 02:49:17', '2017-11-20 03:04:34'),
('Admin1', '2017-11-20 03:53:07', '2017-11-20 03:54:00'),
('Admin1', '2017-11-20 04:02:13', '2017-11-20 04:03:04'),
('Admin1', '2017-11-20 04:25:32', '2017-11-20 04:25:59'),
('admin1', '2017-11-20 05:00:25', '2017-11-20 05:23:53'),
('admin1', '2017-11-20 05:27:16', '2017-11-20 05:28:35'),
('Admin1', '2017-11-20 05:33:49', '2017-11-20 05:34:37'),
('Admin1', '2017-11-20 05:38:37', '2017-11-20 05:38:48'),
('Admin1', '2017-11-20 05:39:55', '2017-11-20 05:46:06'),
('Admin1', '2017-11-20 05:52:53', '2017-11-20 05:53:47'),
('Admin1', '2017-11-20 06:16:10', '2017-11-20 06:18:42'),
('admin1', '2017-11-20 06:22:49', '2017-11-20 06:22:55'),
('Admin1', '2017-11-20 06:50:25', '2017-11-20 06:53:50'),
('admin1', '2017-11-20 07:36:05', '2017-11-20 07:36:40'),
('admin1', '2017-11-20 07:38:35', '2017-11-20 07:39:39'),
('admin1', '2017-11-20 07:40:41', '2017-11-20 07:44:42'),
('admin1', '2017-11-20 07:45:47', '2017-11-20 07:46:53'),
('Admin1', '2017-11-20 07:47:40', '2017-11-20 07:51:39'),
('Admin1', '2017-11-20 07:55:03', '2017-11-20 07:58:51'),
('Admin1', '2017-11-20 08:00:25', '2017-11-20 08:02:01'),
('admin1', '2017-11-20 08:02:59', '2017-11-20 08:21:43'),
('admin1', '2017-11-20 08:34:30', '2017-11-20 08:35:19'),
('Admin1', '2017-11-20 08:42:23', '2017-11-20 08:44:51'),
('Admin1', '2017-11-20 09:10:03', '2017-11-20 09:10:33'),
('admin1', '2017-11-20 09:16:01', '2017-11-20 09:29:12'),
('Admin1', '2017-11-20 09:31:21', '2017-11-20 10:33:08');

-- --------------------------------------------------------

--
-- Table structure for table `maintainance`
--

CREATE TABLE `maintainance` (
  `taskNo` int(11) NOT NULL,
  `serialNo` varchar(20) NOT NULL,
  `description` varchar(100) NOT NULL,
  `assignedTo` varchar(50) NOT NULL,
  `assignedDate` date NOT NULL,
  `compDate` date NOT NULL,
  `proCost` double NOT NULL,
  `serviceCost` double NOT NULL,
  `remarks` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `maintainance`
--

INSERT INTO `maintainance` (`taskNo`, `serialNo`, `description`, `assignedTo`, `assignedDate`, `compDate`, `proCost`, `serviceCost`, `remarks`) VALUES
(1, 'DG53FSFS34', 'over freezing', 'Dimuth Lanka pvt ltd', '2017-11-03', '2017-11-11', 15000, 2600, 'processing'),
(2, 'E57G6AS89A', 'hard disk failure', 'AXY pvt ltd', '2017-11-02', '2017-11-17', 2600, 1000, 'completed'),
(3, 'Q34TG654D3', 'Q34TG654D3', 'ABC pvt ltd', '2017-11-04', '2017-11-17', 2600, 1000, 'processing'),
(4, 'GUE24J64D9', 'GUE24J64D9', 'RER', '2017-11-10', '2017-11-17', 2000, 0, 'removed');

-- --------------------------------------------------------

--
-- Table structure for table `medicalhistory`
--

CREATE TABLE `medicalhistory` (
  `recID` int(11) NOT NULL,
  `patientID` varchar(10) NOT NULL,
  `height` float NOT NULL,
  `weight` float NOT NULL,
  `allergies` varchar(100) NOT NULL,
  `sugicalHis` varchar(100) NOT NULL,
  `specialIssues` varchar(100) NOT NULL,
  `medications` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `medicalhistory`
--

INSERT INTO `medicalhistory` (`recID`, `patientID`, `height`, `weight`, `allergies`, `sugicalHis`, `specialIssues`, `medications`) VALUES
(1, '1', 156, 60, 'food poision', 'aaaa', 'diabetiest', 'setricine'),
(2, '2', 9, 8, 'fodiara', 'eew', 'hearattack', 'cardi'),
(3, '4', 250, 100, 'People', 'Ciserian', 'getting pregnent while pregnent', 'Shenal\nBrayan\nPramodh\nPraveen');

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `orderCode` int(10) NOT NULL,
  `dateOfPurchase` date DEFAULT NULL,
  `qtyOrdered` int(11) DEFAULT NULL,
  `qtyRecieved` int(11) DEFAULT NULL,
  `status` varchar(10) DEFAULT 'Incomplete',
  `drugCode` varchar(10) NOT NULL,
  `suppName` varchar(20) NOT NULL,
  `dateOfExp` date DEFAULT NULL,
  `dateRecieved` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `orders`
--

INSERT INTO `orders` (`orderCode`, `dateOfPurchase`, `qtyOrdered`, `qtyRecieved`, `status`, `drugCode`, `suppName`, `dateOfExp`, `dateRecieved`) VALUES
(9, '2017-11-10', 3, 56, 'Recieved', 'DR00092', 'himeshika', '2017-11-25', NULL),
(12, '2017-11-08', 17, NULL, 'Incomplete', 'DR00104', 'Shalindri', NULL, NULL),
(13, '2017-11-10', 17, 56, 'Recieved', 'DR00104', 'himeshika', '2017-11-04', NULL),
(14, '2017-11-10', 13, NULL, 'Incomplete', 'DR00105', 'himeshika', NULL, NULL),
(15, '2017-11-10', 12, 45, 'Recieved', 'DR00104', 'wajitha', '2017-11-23', NULL),
(16, '2017-11-24', 69, NULL, 'Incomplete', 'DR00103', 'Shalindri', NULL, NULL),
(17, '2017-11-03', 5, NULL, 'Incomplete', 'DR00103', 'wajitha', NULL, NULL),
(18, '2017-11-03', 13, NULL, 'Incomplete', 'DR00104', 'wajitha', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `otherstaff`
--

CREATE TABLE `otherstaff` (
  `empNo` varchar(10) NOT NULL,
  `NICNo` varchar(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `position` varchar(20) NOT NULL,
  `Dob` date NOT NULL,
  `Gender` varchar(6) NOT NULL,
  `Date_of_Join` date NOT NULL,
  `ContactNo` varchar(11) NOT NULL,
  `address` varchar(50) NOT NULL,
  `basicSal` double DEFAULT NULL,
  `otRate` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `otherstaff`
--

INSERT INTO `otherstaff` (`empNo`, `NICNo`, `name`, `position`, `Dob`, `Gender`, `Date_of_Join`, `ContactNo`, `address`, `basicSal`, `otRate`) VALUES
('EMP002', '222222222v', 'gggg', 'Lab Assistant', '2017-09-29', 'male', '2017-09-08', '2354545666', 'het', 35000, 262.5),
('EMP003', '111111111v', 'Himeshika abeyratna', 'Lab Assistant', '1986-09-10', 'female', '2017-09-15', '0714522222', 'No 10, Ja Ela', 25000, 187.5),
('EMP007', '333333333v', 'Sachinin Epa', 'Pharmacist', '1989-09-13', 'female', '2017-09-12', '0117754233', 'No 10, Kalutara', 25000, 187.5),
('EMP011', '444444444v', 'Shalindri Perera', 'Receptionist', '1996-11-08', 'female', '2017-11-06', '0714452336', 'Wattala', 23000, 172.5),
('EMP013', '956752739v', 'Kamal Gamage', 'Pharmacist', '1980-11-02', 'male', '2017-08-01', '0778365265', 'Kandana', NULL, NULL),
('EMP014', '666666666v', 'Nimali Fernando', 'Pharmacist', '1986-08-01', 'male', '2017-11-05', '0744456634', 'Panadura', NULL, NULL),
('EMP018', '777777777v', 'Sanduni Perera', 'Lab Assistant', '1986-11-01', 'male', '2017-11-07', '0177725349', 'Dehiwala', 24000, 180),
('EMP019', '777777777v', 'Sanduni Perera', 'Lab Assistant', '1985-11-01', 'male', '2017-11-07', '0177725349', 'Dehiwala', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patientID` int(10) NOT NULL,
  `name` varchar(50) NOT NULL,
  `dob` date NOT NULL,
  `contactNo` varchar(11) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `address` varchar(50) NOT NULL,
  `email` varchar(25) NOT NULL,
  `bgroup` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patientID`, `name`, `dob`, `contactNo`, `gender`, `address`, `email`, `bgroup`) VALUES
(1, 'Himeshika Abayarathne', '1996-09-28', '0712801032', 'female', 'Kandana', 'himeshika@gmail.com', 'A+'),
(4, 'Ruklani Aththanayaka', '1996-11-30', '0771235454', 'female', 'Kuliyapitiya', 'rubiwee', 'AB+'),
(5, 'Tiffany', '1997-05-30', '0778938717', 'female', 'Ja ela', 'tifi@gmail.com', 'B+'),
(6, 'Stefinie Minoli', '1998-07-08', '0712155716', 'female', 'Kaluthara', 'ssm.fernando@gmail.com', 'B-'),
(7, 'Shenal', '1995-11-13', '0712688145', 'male', 'kandana', 'shenal.sj@gmail.com', 'B+'),
(8, 'Shalindri', '1995-11-13', '0719393822', 'female', 'Kuliya[itya', 'ruklani@yahoo.com', 'A+');

-- --------------------------------------------------------

--
-- Table structure for table `paychanel`
--

CREATE TABLE `paychanel` (
  `BiLL` int(11) NOT NULL,
  `apRefNo` varchar(10) NOT NULL,
  `PID` varchar(50) NOT NULL,
  `PName` varchar(50) NOT NULL,
  `DocName` varchar(50) NOT NULL,
  `HosFee` float NOT NULL,
  `DocFee` float NOT NULL,
  `Total` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paychanel`
--

INSERT INTO `paychanel` (`BiLL`, `apRefNo`, `PID`, `PName`, `DocName`, `HosFee`, `DocFee`, `Total`) VALUES
(1, '5', '1', '', '', 400, 1500, 1900),
(2, 'AP006', '3', '', '', 400, 1500, 1900),
(3, 'AP010', '3', '', '', 400, 1500, 1900),
(4, 'AP006', '3', 'blaaaaaa', 'Prasad Katulanda', 400, 1500, 1900),
(5, 'AP014', '6', 'Stefinie Minoli', 'Prasad Katulanda', 400, 1500, 1900),
(6, 'AP016', '1', 'Himeshika Abayarathne', 'Ayodhya Dayananda', 400, 1000, 1400),
(7, 'AP016', '1', 'Himeshika Abayarathne', 'Ayodhya Dayananda', 400, 1000, 1400);

-- --------------------------------------------------------

--
-- Table structure for table `paydrugs`
--

CREATE TABLE `paydrugs` (
  `BiLL` int(11) NOT NULL,
  `PresNo` varchar(50) NOT NULL,
  `DrugC` varchar(500) NOT NULL,
  `DrugN` varchar(500) NOT NULL,
  `TotQty` int(11) NOT NULL,
  `totCost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paydrugs`
--

INSERT INTO `paydrugs` (`BiLL`, `PresNo`, `DrugC`, `DrugN`, `TotQty`, `totCost`) VALUES
(1, 'PR56', ',DR00022,DR00022,DR00022,DR00022,DR00022,DR00022,DR00022,DR00022,DR00022,DR00022,DR00022,DR00040,DR00022,DR00022,DR00022,DR00022,DR00022,DR00022,DR00040,DR00022,DR00022,DR00022', ',Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Paradeen 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Panadol 100,Paradeen 100,Panadol 100,Panadol 100,Panadol 100', 1567, 138673),
(2, '6', ',DR00106,DR00108', ',Atopica 100,Minocin 250', 38, 4876);

-- --------------------------------------------------------

--
-- Table structure for table `payequip`
--

CREATE TABLE `payequip` (
  `BiLL` int(11) NOT NULL,
  `sno` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `type` varchar(50) NOT NULL,
  `manuf` varchar(50) NOT NULL,
  `supp` varchar(50) NOT NULL,
  `price` float NOT NULL,
  `qty` int(11) NOT NULL,
  `tcost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `payequip`
--

INSERT INTO `payequip` (`BiLL`, `sno`, `name`, `type`, `manuf`, `supp`, `price`, `qty`, `tcost`) VALUES
(1, 'asd123', 'hfgdsjgfjgdsj', 'Diagnostics', 'fbdkndgg', 'fsdfs', 11212, 12, 134544),
(2, 'DE35G3D45D', 'microscope', 'Laboratory Equipments', 'Greenware', 'SACHINI', 25000, 2, 50000),
(3, 'DE35G3D45D', 'microscope', 'Laboratory Equipments', 'Greenware', 'SACHINI', 25000, 2, 50000);

-- --------------------------------------------------------

--
-- Table structure for table `paylab`
--

CREATE TABLE `paylab` (
  `BiLL` int(11) NOT NULL,
  `PName` varchar(50) NOT NULL,
  `TNum` int(11) NOT NULL,
  `TName` varchar(50) NOT NULL,
  `Charge` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `paymaintain`
--

CREATE TABLE `paymaintain` (
  `BiLL` int(11) NOT NULL,
  `Equipment_No` varchar(20) NOT NULL,
  `Product_Cost` float NOT NULL,
  `Service_Cost` float NOT NULL,
  `Total_Cost` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymaintain`
--

INSERT INTO `paymaintain` (`BiLL`, `Equipment_No`, `Product_Cost`, `Service_Cost`, `Total_Cost`) VALUES
(1, 'aa11122', 543534, 10, 543544),
(2, 'DG53FSFS34', 15000, 2600, 17600),
(3, 'DG53FSFS34', 15000, 2600, 17600);

-- --------------------------------------------------------

--
-- Table structure for table `paysup`
--

CREATE TABLE `paysup` (
  `BiLL` int(11) NOT NULL,
  `DrugC` varchar(50) NOT NULL,
  `DrugN` varchar(50) NOT NULL,
  `DrugTy` varchar(50) NOT NULL,
  `UnitP` float NOT NULL,
  `SellP` float NOT NULL,
  `Qty` int(11) NOT NULL,
  `TotP` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paysup`
--

INSERT INTO `paysup` (`BiLL`, `DrugC`, `DrugN`, `DrugTy`, `UnitP`, `SellP`, `Qty`, `TotP`) VALUES
(1, 'DR00022', 'Panadol 100', 'Tablet', 89, 500, 500, 44500),
(2, 'DR00022', 'Panadol 100', 'Tablet', 89, 500, 500, 44500),
(3, 'DR00102', 'Duramycin 100', 'Liquid', 10, 500, 17, 170),
(4, 'DR00103', 'Calpol 50', 'Tablet', 12.5, 500, 36, 450);

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `presNo` int(10) NOT NULL,
  `date` date NOT NULL,
  `issue` varchar(100) NOT NULL,
  `medicines` varchar(1000) NOT NULL,
  `empNo` varchar(10) NOT NULL,
  `labtests` varchar(500) NOT NULL,
  `patientID` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`presNo`, `date`, `issue`, `medicines`, `empNo`, `labtests`, `patientID`) VALUES
(1, '2017-11-02', 'g', 'y', 'EMP006', 'r', '12'),
(2, '2017-11-02', 'yj', 'uk', 'EMP004', 'JU', '56'),
(3, '2017-11-18', 'Puppe', 'Panadol 100  M:70  E:0  N:10  #days:367  tot:29360,ty  M:70  E:0  N:10  #days:367  tot:29360,Panadol 100  M:6  E:0  N:10  #days:381  tot:6096,sa  M:0  E:17  N:0  #days:381  tot:6477,Panadol 100  M:18  E:35  N:24  #days:381  tot:29337,Panadol 100  M:18  E:35  N:24  #days:381  tot:29337,Panadol 100  M:18  E:35  N:24  #days:381  tot:29337', 'Admin1', 'sfdaf', '1'),
(4, '2017-11-18', 'df', 'Paradeen  M:2  E:0  N:3  #days:2  tot:10,po  M:2  E:0  N:3  #days:2  tot:10', 'Admin1', 'MRI', '3'),
(5, '2017-11-20', 'fever', 'Garamycin 50  M:3  E:0  N:2  #days:3  tot:15,Atopica 100  M:3  E:0  N:2  #days:3  tot:15,Aureomycin 100  M:3  E:0  N:2  #days:3  tot:15', 'EMP004', 'FBC', '4'),
(6, '2017-11-20', 'cold', 'Garamycin 50  M:3  E:0  N:2  #days:3  tot:15,Atopica 100  M:3  E:0  N:2  #days:3  tot:15,Aureomycin 100  M:3  E:0  N:2  #days:3  tot:15,Duramycin 100  M:0  E:0  N:2  #days:3  tot:6,Atopica 100  M:0  E:0  N:2  #days:3  tot:6,Minocin 250  M:0  E:0  N:2  #days:3  tot:6', 'EMP004', 'none', '1'),
(7, '2017-11-20', 'fever', '--Select--  M:3  E:0  N:0  #days:0  tot:0', 'Admin1', 'Full Bloss', '5');

-- --------------------------------------------------------

--
-- Table structure for table `reference`
--

CREATE TABLE `reference` (
  `refNo` varchar(10) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `substance` varchar(20) DEFAULT NULL,
  `refRange` varchar(20) DEFAULT NULL,
  `optimal` varchar(10) DEFAULT NULL,
  `border` varchar(20) DEFAULT NULL,
  `high` varchar(20) DEFAULT NULL,
  `veryhigh` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `refund`
--

CREATE TABLE `refund` (
  `RebillNo` int(11) NOT NULL,
  `apRefNo` int(11) NOT NULL,
  `patientID` int(11) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `regularconsultation`
--

CREATE TABLE `regularconsultation` (
  `empNo` varchar(10) NOT NULL,
  `day` varchar(10) NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `fromDate` date NOT NULL,
  `toDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `salary`
--

CREATE TABLE `salary` (
  `datePayed` date NOT NULL,
  `empNo` varchar(10) NOT NULL,
  `basicSal` double NOT NULL,
  `No_of_leaves` int(11) NOT NULL,
  `seasonalBonus` double NOT NULL,
  `OThrs` float NOT NULL,
  `leaveDed` double NOT NULL,
  `ETF` double NOT NULL,
  `OTpay` double NOT NULL,
  `netSal` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `salary`
--

INSERT INTO `salary` (`datePayed`, `empNo`, `basicSal`, `No_of_leaves`, `seasonalBonus`, `OThrs`, `leaveDed`, `ETF`, `OTpay`, `netSal`) VALUES
('2017-09-23', 'EMP002', 350000, 4, 4500, 5, 0, 14000, 13125, 353625),
('2017-09-25', 'EMP007', 25000, 2, 0, 10, 0, 1000, 1875, 25875),
('2017-11-19', 'EMP003', 25000, 5, 2500, 7.5, 1000, 2000, 1406.25, 25906.25),
('2017-11-20', 'EMP002', 35000, 3, 1000, 7, 0, 2800, 1837.5, 35037.5),
('2017-11-20', 'EMP007', 25000, 5, 0, 5.5, 1000, 2000, 1031.25, 23031.25),
('2017-11-20', 'EMP011', 23000, 6, 1500, 4, 1840, 1840, 690, 21510),
('2017-11-20', 'EMP018', 24000, 6, 1000, 4, 1920, 1920, 720, 21880);

-- --------------------------------------------------------

--
-- Table structure for table `sells`
--

CREATE TABLE `sells` (
  `drugCode` varchar(10) NOT NULL,
  `supCode` varchar(10) NOT NULL,
  `buyPrice` float(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sells`
--

INSERT INTO `sells` (`drugCode`, `supCode`, `buyPrice`) VALUES
('DR00022', 'MBSL', 500),
('DR00022', 'SHEY', 800),
('DR00040', 'MBSL', 800),
('DR00040', 'MikeABS', 200),
('DR00042', 'PIGY', 900),
('DR00105', 'sachini', 8547);

-- --------------------------------------------------------

--
-- Table structure for table `session`
--

CREATE TABLE `session` (
  `sessionID` int(10) NOT NULL,
  `date` date NOT NULL,
  `empNo` varchar(10) NOT NULL,
  `startDate` date NOT NULL,
  `endDate` date NOT NULL,
  `startTime` time NOT NULL,
  `endTime` time NOT NULL,
  `noOfApp` int(11) NOT NULL,
  `avilableApp` int(11) NOT NULL DEFAULT '0',
  `currentAppNo` int(11) NOT NULL DEFAULT '0',
  `fee` double DEFAULT NULL,
  `status` varchar(50) NOT NULL DEFAULT 'Available',
  `TelBooking` varchar(12) NOT NULL,
  `action` varchar(25) NOT NULL DEFAULT 'UnFilled'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `session`
--

INSERT INTO `session` (`sessionID`, `date`, `empNo`, `startDate`, `endDate`, `startTime`, `endTime`, `noOfApp`, `avilableApp`, `currentAppNo`, `fee`, `status`, `TelBooking`, `action`) VALUES
(24, '2017-09-03', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(25, '2017-09-10', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(26, '2017-09-17', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(27, '2017-09-24', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(28, '2017-10-01', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(29, '2017-10-08', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(30, '2017-10-15', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(31, '2017-10-22', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(32, '2017-10-29', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(33, '2017-11-05', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(34, '2017-11-12', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(35, '2017-11-19', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 1, 1, NULL, 'Available', 'Allow', 'UnFilled'),
(36, '2017-11-26', 'EMP004', '2017-09-01', '2017-12-01', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(37, '2017-09-06', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(38, '2017-09-13', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(39, '2017-09-20', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(40, '2017-09-27', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(41, '2017-10-04', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(42, '2017-10-11', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(43, '2017-10-18', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(44, '2017-10-25', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(45, '2017-11-01', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(46, '2017-11-08', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(47, '2017-11-15', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(48, '2017-11-22', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(49, '2017-11-29', 'EMP004', '2017-09-01', '2017-12-01', '18:00:00', '19:00:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(50, '2017-11-07', 'EMP009', '2017-11-01', '2017-11-30', '16:00:00', '17:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(51, '2017-11-14', 'EMP009', '2017-11-01', '2017-11-30', '16:00:00', '17:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(52, '2017-11-21', 'EMP009', '2017-11-01', '2017-11-30', '16:10:00', '17:10:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(53, '2017-11-28', 'EMP009', '2017-11-01', '2017-11-30', '16:00:00', '17:00:00', 4, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(54, '2017-11-02', 'EMP009', '2017-11-01', '2017-11-30', '20:00:00', '21:00:00', 6, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(55, '2017-11-09', 'EMP009', '2017-11-01', '2017-11-30', '20:00:00', '21:00:00', 6, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(56, '2017-11-16', 'EMP009', '2017-11-01', '2017-11-30', '20:00:00', '21:00:00', 6, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(57, '2017-11-23', 'EMP009', '2017-11-01', '2017-11-30', '20:00:00', '21:00:00', 6, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(58, '2017-11-30', 'EMP009', '2017-11-01', '2017-11-30', '20:00:00', '21:00:00', 6, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(59, '2017-11-04', 'EMP012', '2017-10-31', '2017-11-30', '08:00:00', '09:00:00', 15, 0, 0, NULL, 'Available', 'Deny', 'UnFilled'),
(60, '2017-11-11', 'EMP012', '2017-10-31', '2017-11-30', '08:00:00', '09:00:00', 15, 0, 0, NULL, 'Available', 'Deny', 'UnFilled'),
(61, '2017-11-18', 'EMP012', '2017-10-31', '2017-11-30', '08:00:00', '09:00:00', 15, 0, 0, NULL, 'Available', 'Deny', 'UnFilled'),
(62, '2017-11-25', 'EMP012', '2017-10-31', '2017-11-30', '08:00:00', '09:00:00', 15, 2, 3, NULL, 'Available', 'Deny', 'UnFilled'),
(63, '2017-11-06', 'EMP012', '2017-11-01', '2017-11-30', '15:00:00', '16:30:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(64, '2017-11-13', 'EMP012', '2017-11-01', '2017-11-30', '15:00:00', '16:30:00', 10, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(65, '2017-11-20', 'EMP012', '2017-11-01', '2017-11-30', '15:00:00', '16:30:00', 10, 0, 1, NULL, 'Available', 'Allow', 'UnFilled'),
(66, '2017-11-27', 'EMP012', '2017-11-01', '2017-11-30', '15:00:00', '16:30:00', 10, 0, 0, NULL, 'Blocked', 'Allow', 'UnFilled'),
(67, '2017-10-08', 'EMP004', '2017-10-08', '2017-10-08', '16:00:00', '17:00:00', 8, 5, 5, 7500, 'Available', 'Allow', 'UnFilled'),
(68, '2017-10-18', 'EMP004', '2017-10-18', '2017-10-18', '07:00:00', '07:30:00', 3, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(69, '2017-12-03', 'EMP020', '2017-12-01', '2017-12-31', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Blocked', 'Allow', 'UnFilled'),
(70, '2017-12-10', 'EMP020', '2017-12-01', '2017-12-31', '07:00:00', '08:00:00', 5, 1, 1, NULL, 'Available', 'Allow', 'UnFilled'),
(71, '2017-12-17', 'EMP020', '2017-12-01', '2017-12-31', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(72, '2017-12-24', 'EMP020', '2017-12-01', '2017-12-31', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(73, '2017-12-31', 'EMP020', '2017-12-01', '2017-12-31', '07:00:00', '08:00:00', 5, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(74, '2017-12-06', 'EMP020', '2017-12-01', '2017-12-31', '08:00:00', '09:00:00', 2, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(75, '2017-12-13', 'EMP020', '2017-12-01', '2017-12-31', '08:00:00', '09:00:00', 2, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(76, '2017-12-20', 'EMP020', '2017-12-01', '2017-12-31', '08:00:00', '09:00:00', 2, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(77, '2017-12-27', 'EMP020', '2017-12-01', '2017-12-31', '08:00:00', '09:00:00', 2, 0, 0, NULL, 'Available', 'Allow', 'UnFilled'),
(78, '2017-11-30', 'EMP020', '2017-11-30', '2017-11-30', '18:00:00', '19:00:00', 5, 0, 1, NULL, 'Available', 'Deny', 'UnFilled');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `supCode` int(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `SupplierAddress` varchar(255) NOT NULL,
  `SupplierEmail` varchar(255) NOT NULL,
  `SupplierTelphoneNo` varchar(255) NOT NULL,
  `Fax` varchar(255) NOT NULL,
  `DateOfJoin` date NOT NULL,
  `rating` varchar(250) NOT NULL,
  `remark` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`supCode`, `name`, `SupplierAddress`, `SupplierEmail`, `SupplierTelphoneNo`, `Fax`, `DateOfJoin`, `rating`, `remark`) VALUES
(7, 'Shalindri', 'Wattala', 'shalindri20@gmail.com', '0716531610', '0112346554', '2017-11-17', 'Excellent', 'vbcv'),
(8, 'himeshika', 'jaela', 'hishi1996@gmail.com', '0712801032', '0112228417', '2016-11-01', 'Excellent', 'good'),
(10, 'wajitha', 'wattala', 'hishi1996@gmail.com', '0712802653', '0112228478', '2016-11-24', 'Excellent', 'good'),
(11, 'sachini', 'kaluthara', 'sachi.epa96@gmail.com', '0723237310', '0112939597', '2017-11-04', 'Excellent', 'good'),
(12, 'shenal', 'kandana', 'shenalsj@gmail.com', '0712356891', '0112365478', '2017-11-11', 'Excellent', 'good'),
(13, 'MBSL', 'Colombo 7', 'ayodhya.dayananda@my.sliit.lk', '0112225378', '0112223378', '2017-09-01', 'Excellent', 'good'),
(14, 'SCC', 'kandana', 'sdalsj@gmail.com', '0712356891', '0112365478', '2017-11-11', 'Excellent', 'good');

-- --------------------------------------------------------

--
-- Table structure for table `supply`
--

CREATE TABLE `supply` (
  `supCode` varchar(10) NOT NULL,
  `serialNo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `test`
--

CREATE TABLE `test` (
  `testNo` int(10) NOT NULL,
  `patientName` varchar(10) NOT NULL,
  `conatctNo` varchar(11) NOT NULL,
  `age` int(11) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `pcategory` varchar(50) NOT NULL,
  `date` date NOT NULL,
  `time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(50) NOT NULL,
  `speciman` varchar(20) NOT NULL,
  `charge` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `test`
--

INSERT INTO `test` (`testNo`, `patientName`, `conatctNo`, `age`, `gender`, `pcategory`, `date`, `time`, `type`, `speciman`, `charge`) VALUES
(2, 'stephim', '0112245958', 18, 'F', 'Unregistered', '2017-09-14', '2017-11-19 18:30:12', 'ECG', 'Null', 2000),
(3, 'sachini', '0778938717', 34, 'M', 'Unregistered', '2017-09-01', '2017-11-19 18:45:00', 'Lipid Profile', 'Blood', 1500),
(4, 'fhyf', '072987876', 58, 'M', 'Unregistered', '2017-09-14', '2017-11-19 18:30:12', 'Lipid Profile', 'Blood', 1500),
(5, 'Sanjana', '0112245953', 21, 'F', 'Registered', '2017-11-20', '2017-11-20 09:21:40', 'UFR', 'Urine', 1500),
(6, 'A.P.Manel', '0714567890', 56, 'F', 'Unregistered', '2017-11-20', '2017-11-20 09:26:19', 'Lipid Profile', 'Blood', 1500),
(7, 'Sawmi', '0114532459', 21, 'F', 'Registered', '2017-11-20', '2017-11-20 10:11:50', 'Lipid Profile', 'Blood', 1500);

-- --------------------------------------------------------

--
-- Table structure for table `urineresults`
--

CREATE TABLE `urineresults` (
  `urID` int(10) NOT NULL,
  `colour` varchar(50) NOT NULL,
  `apperance` varchar(50) NOT NULL,
  `gravity` varchar(50) NOT NULL,
  `reaction` varchar(50) NOT NULL,
  `protein` varchar(50) NOT NULL,
  `sugar` varchar(50) NOT NULL,
  `urobilin` varchar(50) NOT NULL,
  `bilirubin` varchar(50) NOT NULL,
  `ketone` varchar(50) NOT NULL,
  `puscells` varchar(50) NOT NULL,
  `redcells` varchar(50) NOT NULL,
  `epcells` varchar(50) NOT NULL,
  `organisms` varchar(50) NOT NULL,
  `crystals` varchar(50) NOT NULL,
  `casts` varchar(50) NOT NULL,
  `other` varchar(50) NOT NULL,
  `testNo` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `urineresults`
--

INSERT INTO `urineresults` (`urID`, `colour`, `apperance`, `gravity`, `reaction`, `protein`, `sugar`, `urobilin`, `bilirubin`, `ketone`, `puscells`, `redcells`, `epcells`, `organisms`, `crystals`, `casts`, `other`, `testNo`) VALUES
(1, 'Colourless', 'Slightly Tubid', '1.020', 'pH 7.0', 'Trace(0.15)', '+(15)', 'NIL', 'NIL', 'NIL', 'negative', 'negative', 'NIL', 'few', 'Uric Acid', 'Hyline', 'NIL', '5');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`username`, `password`, `role`) VALUES
('Admin1', 'admin123', 'Admin'),
('EMP009', '0773575473', 'Doctor'),
('EMP011', '444444444v', 'Receptionist'),
('EMP012', '0174569982', 'Doctor'),
('EMP013', '956752739v', 'Pharmacist'),
('EMP014', '666666666v', 'Pharmacist'),
('EMP015', '0177725349', 'Doctor'),
('EMP017', '0177725349', 'Doctor'),
('EMP018', '777777777v', 'Lab Assistant'),
('EMP019', '777777777v', 'Lab Assistant'),
('EMP020', '0177725349', 'Doctor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appointment`
--
ALTER TABLE `appointment`
  ADD PRIMARY KEY (`apRefNo`);

--
-- Indexes for table `appseq`
--
ALTER TABLE `appseq`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `assests`
--
ALTER TABLE `assests`
  ADD PRIMARY KEY (`serialNo`);

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billNo`);

--
-- Indexes for table `bloodresults`
--
ALTER TABLE `bloodresults`
  ADD PRIMARY KEY (`brID`);

--
-- Indexes for table `buy`
--
ALTER TABLE `buy`
  ADD PRIMARY KEY (`drugCode`,`patientID`,`prescriptionNo`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`empNo`),
  ADD UNIQUE KEY `RegNo` (`RegNo`);

--
-- Indexes for table `drugs`
--
ALTER TABLE `drugs`
  ADD PRIMARY KEY (`drugCode`);

--
-- Indexes for table `drug_seq`
--
ALTER TABLE `drug_seq`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `emp_seq`
--
ALTER TABLE `emp_seq`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `eqsupplier`
--
ALTER TABLE `eqsupplier`
  ADD PRIMARY KEY (`supid`);

--
-- Indexes for table `equiptypename`
--
ALTER TABLE `equiptypename`
  ADD PRIMARY KEY (`no`);

--
-- Indexes for table `issued`
--
ALTER TABLE `issued`
  ADD PRIMARY KEY (`issueNo`);

--
-- Indexes for table `labreports`
--
ALTER TABLE `labreports`
  ADD PRIMARY KEY (`labTestNo`);

--
-- Indexes for table `log`
--
ALTER TABLE `log`
  ADD PRIMARY KEY (`userName`,`login`);

--
-- Indexes for table `maintainance`
--
ALTER TABLE `maintainance`
  ADD PRIMARY KEY (`taskNo`);

--
-- Indexes for table `medicalhistory`
--
ALTER TABLE `medicalhistory`
  ADD PRIMARY KEY (`recID`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`orderCode`);

--
-- Indexes for table `otherstaff`
--
ALTER TABLE `otherstaff`
  ADD PRIMARY KEY (`empNo`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patientID`);

--
-- Indexes for table `paychanel`
--
ALTER TABLE `paychanel`
  ADD PRIMARY KEY (`BiLL`);

--
-- Indexes for table `paydrugs`
--
ALTER TABLE `paydrugs`
  ADD PRIMARY KEY (`BiLL`);

--
-- Indexes for table `payequip`
--
ALTER TABLE `payequip`
  ADD PRIMARY KEY (`BiLL`);

--
-- Indexes for table `paylab`
--
ALTER TABLE `paylab`
  ADD PRIMARY KEY (`BiLL`);

--
-- Indexes for table `paymaintain`
--
ALTER TABLE `paymaintain`
  ADD PRIMARY KEY (`BiLL`);

--
-- Indexes for table `paysup`
--
ALTER TABLE `paysup`
  ADD PRIMARY KEY (`BiLL`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`presNo`);

--
-- Indexes for table `reference`
--
ALTER TABLE `reference`
  ADD PRIMARY KEY (`refNo`);

--
-- Indexes for table `refund`
--
ALTER TABLE `refund`
  ADD PRIMARY KEY (`RebillNo`);

--
-- Indexes for table `regularconsultation`
--
ALTER TABLE `regularconsultation`
  ADD PRIMARY KEY (`empNo`);

--
-- Indexes for table `salary`
--
ALTER TABLE `salary`
  ADD PRIMARY KEY (`datePayed`,`empNo`);

--
-- Indexes for table `sells`
--
ALTER TABLE `sells`
  ADD PRIMARY KEY (`drugCode`,`supCode`);

--
-- Indexes for table `session`
--
ALTER TABLE `session`
  ADD PRIMARY KEY (`sessionID`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supCode`);

--
-- Indexes for table `supply`
--
ALTER TABLE `supply`
  ADD PRIMARY KEY (`supCode`,`serialNo`);

--
-- Indexes for table `test`
--
ALTER TABLE `test`
  ADD PRIMARY KEY (`testNo`);

--
-- Indexes for table `urineresults`
--
ALTER TABLE `urineresults`
  ADD PRIMARY KEY (`urID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appseq`
--
ALTER TABLE `appseq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `bloodresults`
--
ALTER TABLE `bloodresults`
  MODIFY `brID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `drug_seq`
--
ALTER TABLE `drug_seq`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=126;
--
-- AUTO_INCREMENT for table `emp_seq`
--
ALTER TABLE `emp_seq`
  MODIFY `ID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT for table `issued`
--
ALTER TABLE `issued`
  MODIFY `issueNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `maintainance`
--
ALTER TABLE `maintainance`
  MODIFY `taskNo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `medicalhistory`
--
ALTER TABLE `medicalhistory`
  MODIFY `recID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `orderCode` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `patientID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT for table `paychanel`
--
ALTER TABLE `paychanel`
  MODIFY `BiLL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `paydrugs`
--
ALTER TABLE `paydrugs`
  MODIFY `BiLL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `payequip`
--
ALTER TABLE `payequip`
  MODIFY `BiLL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `paylab`
--
ALTER TABLE `paylab`
  MODIFY `BiLL` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `paymaintain`
--
ALTER TABLE `paymaintain`
  MODIFY `BiLL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `paysup`
--
ALTER TABLE `paysup`
  MODIFY `BiLL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `presNo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `session`
--
ALTER TABLE `session`
  MODIFY `sessionID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=79;
--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `supCode` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT for table `test`
--
ALTER TABLE `test`
  MODIFY `testNo` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `urineresults`
--
ALTER TABLE `urineresults`
  MODIFY `urID` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
