-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 23, 2024 lúc 12:43 PM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `tour_reservation`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `account`
--

CREATE TABLE `account` (
  `account_id` int(11) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL,
  `phone_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `account`
--

INSERT INTO `account` (`account_id`, `user_name`, `password`, `email`, `time`, `status`, `phone_number`) VALUES
(1, 'duongngocle4231', 'Lengocduong.22062003', 'lengocduong003@gmail.com', '2024-10-23 16:50:10', 1, NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

CREATE TABLE `category` (
  `category_id` int(11) NOT NULL,
  `category_name` varchar(255) NOT NULL,
  `category_detail` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`category_id`, `category_name`, `category_detail`, `status`) VALUES
(1, 'Du lịch trong nước', NULL, 1),
(2, 'Du lịch ngoài nước', NULL, 1),
(3, 'Du lịch Châu Á', NULL, 1),
(4, 'Du lịch sale', NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `customer`
--

CREATE TABLE `customer` (
  `customer_id` int(11) NOT NULL,
  `account_id` int(11) DEFAULT NULL,
  `relationship_id` int(11) DEFAULT NULL,
  `relationship_name` varchar(255) DEFAULT NULL,
  `customer_name` varchar(255) NOT NULL,
  `customer_type` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `visa_expire` date DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `customer`
--

INSERT INTO `customer` (`customer_id`, `account_id`, `relationship_id`, `relationship_name`, `customer_name`, `customer_type`, `sex`, `phone_number`, `email`, `address`, `birthday`, `visa_expire`, `status`) VALUES
(1, NULL, NULL, '', 'Dương Ngọc Lê ', 'Trẻ em', 1, '0963717300', 'lengocduong4231@gmail.com', 'Tân Phú', NULL, NULL, 1),
(2, NULL, NULL, 'duong le', 'Lê Ngọc Dương', 'Trẻ em', 0, '0963717300', 'lengocduong003@gmail.com', 'tan binh', NULL, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `employee`
--

CREATE TABLE `employee` (
  `employee_id` int(11) NOT NULL,
  `account_id` int(11) NOT NULL,
  `employee_name` varchar(255) NOT NULL,
  `birthday` date DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `employee`
--

INSERT INTO `employee` (`employee_id`, `account_id`, `employee_name`, `birthday`, `phone_number`) VALUES
(1, 1, 'Lê Ngọc Dương', '2003-06-22', '0963717300');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image`
--

CREATE TABLE `image` (
  `image_id` int(11) NOT NULL,
  `tour_id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `image`
--

INSERT INTO `image` (`image_id`, `tour_id`, `url`, `status`) VALUES
(1, 5, '/admin/images/0.jpg', 0),
(2, 5, '/admin/images/1.jpg', 1),
(3, 5, '/admin/images/2.jpg', 2),
(4, 6, '/admin/images/0.jpg', 0),
(5, 6, '/admin/images/1.jpg', 1),
(6, 6, '/admin/images/2.jpg', 2),
(7, 7, '/admin/images/0.png', 0),
(8, 7, '/admin/images/1.jpg', 1),
(9, 7, '/admin/images/2.jpg', 2),
(10, 8, '/admin/images/0.jpg', 0),
(11, 8, '/admin/images/1.jpg', 1),
(12, 8, '/admin/images/2.jpg', 2),
(13, 9, '/admin/images/logo.jpg', 0),
(14, 9, '/admin/images/2.jpg', 1),
(15, 9, '/admin/images/2.jpg', 2),
(16, 10, '/admin/images/FP_lKGaXEAEDshm.png', 0),
(17, 10, '/admin/images/2.jpg', 1),
(18, 10, '/admin/images/0.jpg', 2),
(19, 11, '/admin/images/2.jpg', 0),
(20, 11, '/admin/images/2.jpg', 1),
(21, 11, '/admin/images/2.jpg', 2),
(22, 12, '/admin/images/1.jpg', 0),
(24, 12, '/admin/images/2.jpg', 2),
(28, 3, '/admin/images/0.jpg', 0),
(29, 4, '/admin/images/0.jpg', 0),
(30, 13, '/admin/images/2.jpg', 0),
(31, 14, '/admin/images/hinh-anh-ly-cafe-dep-1.jpg', 0),
(32, 1, '/admin/images/2.jpg', 0),
(54, 1, '/admin/images/5014_xuat-khau-ca-phe_vao-thi-truong-Nhat-Ban.jpg', 2),
(57, 15, '/admin/images/0.jpg', 0),
(60, 1, '/admin/images/ao.jpg', 1),
(61, 16, '/admin/images/5910452ed1df0f519eb46b7d06bc3bdf.jpg', 0),
(62, 3, '/admin/images/d51557f45b3ff5b4051db64584a23dd4.jpg', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion`
--

CREATE TABLE `promotion` (
  `promotion_id` int(11) NOT NULL,
  `promotion_name` varchar(255) NOT NULL,
  `percentage_adult` double NOT NULL,
  `percentage_child` double NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `promotion`
--

INSERT INTO `promotion` (`promotion_id`, `promotion_name`, `percentage_adult`, `percentage_child`, `start_time`, `end_time`, `status`) VALUES
(1, 'Sale mùa hè Quảng Trị', 5, 0, '2024-10-22 17:36:13', '2024-10-24 17:36:13', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion_detail`
--

CREATE TABLE `promotion_detail` (
  `promotion_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `promotion_detail`
--

INSERT INTO `promotion_detail` (`promotion_id`, `schedule_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reserve`
--

CREATE TABLE `reserve` (
  `reserve_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `reserve_detail` varchar(255) DEFAULT NULL,
  `adult_count` int(11) NOT NULL,
  `child_count` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reserve`
--

INSERT INTO `reserve` (`reserve_id`, `customer_id`, `schedule_id`, `employee_id`, `reserve_detail`, `adult_count`, `child_count`, `price`, `time`, `status`) VALUES
(1, 1, 1, 1, NULL, 1, 0, 20000000, '2024-10-23 12:15:10', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reserve_detail`
--

CREATE TABLE `reserve_detail` (
  `reserve_detail_id` int(11) NOT NULL,
  `reserve_id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `price` int(11) NOT NULL,
  `detail` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `reserve_detail`
--

INSERT INTO `reserve_detail` (`reserve_detail_id`, `reserve_id`, `customer_id`, `price`, `detail`, `status`) VALUES
(1, 1, 1, 20000000, NULL, 1),
(2, 1, 2, 20000000, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `tour_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `tour_name` varchar(255) NOT NULL,
  `tour_detail` varchar(255) DEFAULT NULL,
  `departure_location` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`tour_id`, `category_id`, `tour_name`, `tour_detail`, `departure_location`, `city`, `country`, `status`) VALUES
(1, 3, 'Du lịch Thành Cổ, Thị Xã, Tỉnh Quảng Trị sfdsfds', '', 'TP HN', 'Quảng Trị', 'Việt Nam', 1),
(3, 1, 'Bà nà', '', 'TP HN', 'Đà Nẵng', 'Việt Nam', 1),
(4, 2, 'Thái Lan', '', NULL, 'Bangkok', 'Thái Lan', 1),
(5, 2, 'Du lịch Tượng Nữ Thần Tự Do', '', NULL, 'NewYork', 'Mỹ', 1),
(6, 2, 'Du lịch Mỹ', '', NULL, 'NewYork', 'Mỹ', 1),
(7, 1, 'Du lịch Thành Phố Hồ Chí Minh', '', NULL, 'Hồ Chí Minh', 'Việt Nam', 1),
(8, 1, 'Du lịch Hà Nội', '', NULL, 'Hà Nội', 'Việt Nam', 1),
(9, 1, 'Du lịch Quản Trị', '', NULL, 'Quảng Trị', 'Việt Nam', 1),
(10, 1, 'Du lịch Đông Hà', '', NULL, 'Quảng Trị', 'Việt Nam', 1),
(11, 1, 'Dương', 'dưa', NULL, 'Hồ Chí Minh', 'Việt Nam', 1),
(12, 1, 'Tour du lịch Đà Nẵng', '', NULL, 'Đà Nẵng', 'Việt Nam', 0),
(13, 1, 'Du lịch Nam Định', '', NULL, 'Nam Định', 'Việt Nam', 0),
(14, 1, 'Du lịch Tây Bắc', '', NULL, 'Hà Giang', 'Việt Nam', 0),
(15, 1, 'Du lịch AT', '', NULL, 'Quảng Trị', 'Việt Nam', 1),
(16, 4, 'đá', 'dsa', 'cá', 'dá', 'dsa', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_schedule`
--

CREATE TABLE `tour_schedule` (
  `schedule_id` int(11) NOT NULL,
  `tour_id` int(11) NOT NULL,
  `schedule_name` varchar(255) NOT NULL,
  `departure_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `price_adult` int(11) DEFAULT NULL,
  `price_child` int(11) DEFAULT NULL,
  `visa_expire` date DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_schedule`
--

INSERT INTO `tour_schedule` (`schedule_id`, `tour_id`, `schedule_name`, `departure_date`, `return_date`, `quantity`, `price_adult`, `price_child`, `visa_expire`, `status`) VALUES
(1, 1, 'Du lịch vào mùa hè vui nhất sale', '2003-06-22', '2024-06-07', 355, 3000000, 2000000, NULL, 1),
(2, 1, 'Du lịch vào mùa hè', '2024-06-15', '2024-06-20', 30, 3500000, 2500000, NULL, 1),
(3, 4, 'Du lịch vào mùa đông', '2024-11-01', '2024-11-10', 45, 6000000, 5000000, '2024-11-15', 1),
(4, 3, 'Du lịch mùa thu Bà Nà', '2024-07-04', '2024-07-15', 60, 6000000, 5000000, NULL, 1),
(5, 3, 'Du lịch mùa xuân Bà Nà', '2024-01-04', '2024-01-15', 40, 7000000, 6000000, NULL, 1),
(6, 1, 'Du lịch mùa đông', '2024-10-16', '2024-10-27', 55, 4000000, 3000000, NULL, 1),
(7, 1, 'Du lịch mùa thu', '2024-10-14', '2024-10-18', 0, 0, 0, NULL, 1),
(8, 1, 'Du lịch mùa xuân', '2024-10-16', '2024-10-31', 0, 0, 0, NULL, 1),
(9, 1, 'Du lịch mùa xuân hạ', '2024-10-15', '2024-10-19', 0, 0, 0, NULL, 1),
(10, 1, 'Du lịch vào mùa hè', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(11, 1, 'Du lịch vào mùa hè', '2024-06-05', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(12, 1, 'Du lịch vào mùa hè sôi động', '2024-06-05', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(13, 1, 'Du lịch vào mùa hè sôi động', '2024-06-05', '2024-06-07', 30, 3000000, 2000000, NULL, 0),
(14, 1, 'Du lịch vào mùa hè sôi động', '2024-06-05', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(15, 1, 'Du lịch vào mùa hè sôi động', '2024-06-05', '2024-06-07', 30, 3000000, 2000000, '2024-10-24', 1),
(16, 1, 'Du lịch vào mùa hè', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(17, 1, 'Du lịch vào mùa hè mới', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(18, 1, 'Du lịch vào mùa hè mới nhất', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(19, 1, 'Du lịch vào mùa hè nè', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 0),
(20, 1, 'Du lịch vào mùa hè nhất', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 0),
(21, 1, 'Du lịch vào mùa hè nhất', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 0),
(22, 5, 'Du lịch mùa đông', '2024-10-17', '2024-10-18', 23, 3000000, 2000000, NULL, 1),
(23, 1, 'Du lịch mùa thu vui nhất thế giới', '2024-10-17', '2024-10-19', 30, 2000000, 1500000, NULL, 1),
(24, 1, 'Du lịch vào mùa hè sôi động', '2024-10-22', '2024-10-24', 0, 0, 0, NULL, 1),
(25, 15, 'Du lịch mùa hè', '2024-10-21', '2024-10-25', 45, 20000000, 15000000, NULL, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transport`
--

CREATE TABLE `transport` (
  `transport_id` int(11) NOT NULL,
  `transport_name` varchar(255) NOT NULL,
  `transport_detail` varchar(255) DEFAULT NULL,
  `departure_location` varchar(255) DEFAULT NULL,
  `destination_location` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `transport`
--

INSERT INTO `transport` (`transport_id`, `transport_name`, `transport_detail`, `departure_location`, `destination_location`, `status`) VALUES
(1, 'VJ305', 'Vietjet', 'Sân bay Tân Sơn Nhất, TPHCM', 'Sân bay Phú Bài, Huế', 0),
(2, 'VJ306', 'Vietjet ', 'Sân bay Phú Bài, Huế', 'Sân bay Tân Sơn Nhất, TPHCM', 0),
(3, 'VN204', 'Vietnam ariline', 'Sân bay Phú Quốc, Kiên Giang', 'Sân bay Tân Sơn Nhất, TPHCM', 0),
(4, 'VN407', 'Vietnam airline', 'Sân bay Nội Bài, Hà Nội', 'Sân bay Tân Sơn Nhất, TPHCM', 1),
(5, 'Xe Trần biện', '', 'Bến xe Miền Tây, TPHCM', 'Bến xe Biên Hòa, Đồng Nai', 0),
(6, 'Xe Quang Dũng', NULL, 'Bến Xe Đà Nẵng, Đà Nẵng', 'Bến xe Mỹ Đình, Hà Nội', 1),
(7, 'Xe Quang Dũng', NULL, 'Bến Xe Đà Nẵng, Đà Nẵng', 'Bến xe Bãi Cháy, Quảng Ninh', 1),
(8, 'VJ505', 'Vietjet', 'Sân bay Phú Bài, Huế', 'Sân bay Nội Bài, Hà Nội', 0),
(9, 'Xe Quỳnh Thi', 'Đón tại bến xe', 'Bến xe Mỹ Đình, Hà Nội', 'Bến xe Bãi Cháy, Quảng Ninh', 1),
(10, 'VN704', 'Vietnam ariline', 'Sân bay Tân Sơn Nhất, TPHCM', 'Sân bay Nội Bài, Hà Nội', 0),
(11, 'VJ456', 'Vietjet ', 'Sân bay Nội Bài, Hà Nội', 'Sân bay Phú Quốc, Kiên Giang', 1),
(12, 'Xe Phương Trang', NULL, 'Bến xe Biên Hòa, Đồng Nai', 'Bến xe Bãi Cháy, Quảng Ninh', 1),
(13, 'Xe Quỳnh Thi', NULL, 'Bến xe Mỹ Đình, Hà Nội', 'Bến Xe Đà Nẵng, Đà Nẵng', 1),
(14, 'Xe Phương Trang', 'Đón tại nhà', 'Bến xe Lộc Nga, Lâm Đồng', 'Bến xe Miền Tây, TPHCM', 1),
(15, 'Dương ngọc lê', 'eqw', 'Nhà', 'eqw', 0),
(16, 'hello', 'ẻwerw', 'rưerw', 'rewr', 0),
(17, 'ewqe', 'qưeqwe', 'eqweq', 'ewqe', 0),
(18, 'fewfe', 'few', 'few', 'few', 0),
(19, 'Hshs', 'Shshs', 'Zhsjs', 'Zhsh', 0),
(20, 'Mới nhất nè', 'ửewr', 'rewrwe', 'rewr', 0),
(21, 'ewqew', '', 'eqeqw', 'eqw', 0),
(22, 'dưqd', 'qưeqw', 'qưqewq', 'ewqeqwe', 0),
(23, 'ưqewqe', 'eqwe', 'qưeqe', 'ưqeqw', 0),
(24, 'ưqeqeẻw', 'vdsdsf', 'fewfewewfrewrewrwerw', 'qewqe', 0),
(25, 'trêt', 'tre', 'tẻ', 'tre', 0),
(26, 'rưe', 'rew', 'rưe', 'rew', 0),
(27, 'weqeqeểgr', 'qưdwqdqwwqqw', 'gre', 'vgerg', 0),
(28, 'ẻwfwe', 'fewfwe', 'fewf', 'fewfew', 0),
(29, 'cdscfgfh.', 'ewf', 'bgfgbf', 'ed', 0),
(30, 'fe', '', 'fefwf', 'fwef', 0),
(31, 'dưq', 'ưqdqq', 'dưqd', 'ưqdwqd', 0),
(32, 'đá', 'da', 'dá', 'dá', 0),
(33, 'vsd', '', 'fds', 'fds', 0),
(34, 'fgd', 'gfd', 'gfdg', 'gdf', 0);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transport_detail`
--

CREATE TABLE `transport_detail` (
  `transport_detail_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL,
  `transport_id` int(11) NOT NULL,
  `departure_time` datetime NOT NULL,
  `arrival_time` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `transport_detail`
--

INSERT INTO `transport_detail` (`transport_detail_id`, `schedule_id`, `transport_id`, `departure_time`, `arrival_time`, `status`) VALUES
(1, 1, 9, '2024-10-16 17:47:00', '2024-10-17 17:47:00', 1),
(2, 2, 7, '2024-10-17 17:48:54', '2024-10-19 17:48:54', 1),
(3, 1, 4, '2024-10-18 17:49:15', '2024-10-19 17:49:15', 1),
(4, 1, 2, '2024-10-14 00:14:00', '2024-10-18 00:14:00', 2),
(5, 1, 5, '2024-10-22 00:14:00', '2024-10-19 00:14:00', 2),
(6, 1, 11, '2024-10-20 23:49:00', '2024-10-21 23:49:00', 1),
(9, 1, 12, '2024-10-21 00:48:00', '2024-10-31 00:48:00', 1),
(13, 24, 4, '2024-10-22 14:12:00', '2024-10-25 14:12:00', 1),
(14, 24, 4, '2024-10-23 14:15:00', '2024-10-23 14:15:00', 2),
(15, 25, 4, '2024-10-21 14:36:00', '2024-10-23 14:36:00', 1),
(17, 25, 4, '2024-10-21 14:37:00', '2024-10-24 14:37:00', 2),
(20, 25, 4, '2024-10-21 15:13:00', '2024-10-23 15:13:00', 2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_id`),
  ADD UNIQUE KEY `account_id` (`account_id`),
  ADD UNIQUE KEY `user_name` (`user_name`);

--
-- Chỉ mục cho bảng `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`),
  ADD UNIQUE KEY `category_id` (`category_id`);

--
-- Chỉ mục cho bảng `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`customer_id`),
  ADD UNIQUE KEY `customer_id` (`customer_id`),
  ADD KEY `customer_fk1` (`account_id`),
  ADD KEY `customer_fk2` (`relationship_id`);

--
-- Chỉ mục cho bảng `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`employee_id`),
  ADD UNIQUE KEY `employee_id` (`employee_id`),
  ADD KEY `employee_fk1` (`account_id`);

--
-- Chỉ mục cho bảng `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`image_id`),
  ADD UNIQUE KEY `image_id` (`image_id`),
  ADD KEY `image_fk0` (`tour_id`);

--
-- Chỉ mục cho bảng `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`promotion_id`),
  ADD UNIQUE KEY `promotion_id` (`promotion_id`);

--
-- Chỉ mục cho bảng `promotion_detail`
--
ALTER TABLE `promotion_detail`
  ADD PRIMARY KEY (`promotion_id`,`schedule_id`),
  ADD KEY `promotion_detail_fk1` (`schedule_id`),
  ADD KEY `promotion_detail_fk0` (`promotion_id`);

--
-- Chỉ mục cho bảng `reserve`
--
ALTER TABLE `reserve`
  ADD PRIMARY KEY (`reserve_id`),
  ADD UNIQUE KEY `reserve_id` (`reserve_id`),
  ADD KEY `reserve_fk1` (`customer_id`),
  ADD KEY `reserve_fk2` (`schedule_id`),
  ADD KEY `reserve_fk3` (`employee_id`);

--
-- Chỉ mục cho bảng `reserve_detail`
--
ALTER TABLE `reserve_detail`
  ADD PRIMARY KEY (`reserve_detail_id`),
  ADD KEY `reserve_detail_fk1` (`customer_id`),
  ADD KEY `reserve_detail_fk0` (`reserve_id`);

--
-- Chỉ mục cho bảng `tour`
--
ALTER TABLE `tour`
  ADD PRIMARY KEY (`tour_id`),
  ADD UNIQUE KEY `tour_id` (`tour_id`),
  ADD KEY `tour_fk1` (`category_id`);

--
-- Chỉ mục cho bảng `tour_schedule`
--
ALTER TABLE `tour_schedule`
  ADD PRIMARY KEY (`schedule_id`),
  ADD UNIQUE KEY `schedule_id` (`schedule_id`),
  ADD KEY `tour_schedule_fk1` (`tour_id`);

--
-- Chỉ mục cho bảng `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`transport_id`),
  ADD UNIQUE KEY `transport_id` (`transport_id`);

--
-- Chỉ mục cho bảng `transport_detail`
--
ALTER TABLE `transport_detail`
  ADD PRIMARY KEY (`transport_detail_id`),
  ADD KEY `transport_detail_fk1` (`transport_id`),
  ADD KEY `transport_detail_fk0` (`schedule_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `account`
--
ALTER TABLE `account`
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `image`
--
ALTER TABLE `image`
  MODIFY `image_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=63;

--
-- AUTO_INCREMENT cho bảng `promotion`
--
ALTER TABLE `promotion`
  MODIFY `promotion_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `reserve`
--
ALTER TABLE `reserve`
  MODIFY `reserve_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `reserve_detail`
--
ALTER TABLE `reserve_detail`
  MODIFY `reserve_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `tour`
--
ALTER TABLE `tour`
  MODIFY `tour_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT cho bảng `tour_schedule`
--
ALTER TABLE `tour_schedule`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT cho bảng `transport`
--
ALTER TABLE `transport`
  MODIFY `transport_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT cho bảng `transport_detail`
--
ALTER TABLE `transport_detail`
  MODIFY `transport_detail_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `customer_fk1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  ADD CONSTRAINT `customer_fk2` FOREIGN KEY (`relationship_id`) REFERENCES `customer` (`customer_id`);

--
-- Các ràng buộc cho bảng `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_fk1` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`);

--
-- Các ràng buộc cho bảng `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_fk0` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`);

--
-- Các ràng buộc cho bảng `promotion_detail`
--
ALTER TABLE `promotion_detail`
  ADD CONSTRAINT `promotion_detail_fk0` FOREIGN KEY (`promotion_id`) REFERENCES `promotion` (`promotion_id`),
  ADD CONSTRAINT `promotion_detail_fk1` FOREIGN KEY (`schedule_id`) REFERENCES `tour_schedule` (`schedule_id`);

--
-- Các ràng buộc cho bảng `reserve`
--
ALTER TABLE `reserve`
  ADD CONSTRAINT `reserve_fk1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  ADD CONSTRAINT `reserve_fk2` FOREIGN KEY (`schedule_id`) REFERENCES `tour_schedule` (`schedule_id`),
  ADD CONSTRAINT `reserve_fk3` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`);

--
-- Các ràng buộc cho bảng `reserve_detail`
--
ALTER TABLE `reserve_detail`
  ADD CONSTRAINT `reserve_detail_fk0` FOREIGN KEY (`reserve_id`) REFERENCES `reserve` (`reserve_id`),
  ADD CONSTRAINT `reserve_detail_fk1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`);

--
-- Các ràng buộc cho bảng `tour`
--
ALTER TABLE `tour`
  ADD CONSTRAINT `tour_fk1` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);

--
-- Các ràng buộc cho bảng `tour_schedule`
--
ALTER TABLE `tour_schedule`
  ADD CONSTRAINT `tour_schedule_fk1` FOREIGN KEY (`tour_id`) REFERENCES `tour` (`tour_id`);

--
-- Các ràng buộc cho bảng `transport_detail`
--
ALTER TABLE `transport_detail`
  ADD CONSTRAINT `transport_detail_fk0` FOREIGN KEY (`schedule_id`) REFERENCES `tour_schedule` (`schedule_id`),
  ADD CONSTRAINT `transport_detail_fk1` FOREIGN KEY (`transport_id`) REFERENCES `transport` (`transport_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
