-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th10 09, 2024 lúc 04:19 AM
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
  `sex` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `visa_expire` date DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion`
--

CREATE TABLE `promotion` (
  `promotion_id` int(11) NOT NULL,
  `promotion_name` varchar(255) NOT NULL,
  `percentage` double NOT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion_detail`
--

CREATE TABLE `promotion_detail` (
  `promotion_id` int(11) NOT NULL,
  `schedule_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour`
--

CREATE TABLE `tour` (
  `tour_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `tour_name` varchar(255) NOT NULL,
  `tour_detail` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour`
--

INSERT INTO `tour` (`tour_id`, `category_id`, `tour_name`, `tour_detail`, `city`, `country`, `status`) VALUES
(1, 1, 'Du lịch Thành Cổ, Quảng Trị', NULL, 'Quảng Trị', 'Việt Nam', 1),
(3, 1, 'Bà nà', NULL, 'Đà Nẵng', 'Việt Nam', 1),
(4, 2, 'Thái Lan', NULL, 'Bangkok', 'Thái Lan', 1);

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
  `quantity` int(11) NOT NULL,
  `price_adult` int(11) DEFAULT NULL,
  `price_child` int(11) DEFAULT NULL,
  `visa_expire` date DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `tour_schedule`
--

INSERT INTO `tour_schedule` (`schedule_id`, `tour_id`, `schedule_name`, `departure_date`, `return_date`, `quantity`, `price_adult`, `price_child`, `visa_expire`, `status`) VALUES
(1, 1, 'Du lịch vào mùa hè', '2024-06-01', '2024-06-07', 30, 3000000, 2000000, NULL, 1),
(2, 1, 'Du lịch vào mùa hè', '2024-06-15', '2024-06-20', 30, 3500000, 2500000, NULL, 1),
(3, 4, 'Du lịch vào mùa đông', '2024-11-01', '2024-11-10', 45, 6000000, 5000000, '2024-11-15', 1),
(4, 3, 'Du lịch mùa thu Bà Nà', '2024-07-04', '2024-07-15', 60, 6000000, 5000000, NULL, 1),
(5, 3, 'Du lịch mùa xuân Bà Nà', '2024-01-04', '2024-01-15', 40, 7000000, 6000000, NULL, 1);

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
(10, 'VN704', 'Vietnam ariline', 'Sân bay Tân Sơn Nhất, TPHCM', 'Sân bay Nội Bài, Hà Nội', 1),
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
(27, 'weqeqeểgr', 'qưdwqdqwwqqw', 'gre', 'vgerg', 1),
(28, 'ẻwfwe', 'fewfwe', 'fewf', 'fewfew', 1),
(29, 'cdscfgfh.', 'ewf', 'bgfgbf', 'ed', 0),
(30, 'fe', '', 'fefwf', 'fwef', 0),
(31, 'dưq', 'ưqdqq', 'dưqd', 'ưqdwqd', 0),
(32, 'đá', 'da', 'dá', 'dá', 0);

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
  MODIFY `account_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `customer`
--
ALTER TABLE `customer`
  MODIFY `customer_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `employee`
--
ALTER TABLE `employee`
  MODIFY `employee_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `image`
--
ALTER TABLE `image`
  MODIFY `image_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `promotion`
--
ALTER TABLE `promotion`
  MODIFY `promotion_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `reserve`
--
ALTER TABLE `reserve`
  MODIFY `reserve_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `reserve_detail`
--
ALTER TABLE `reserve_detail`
  MODIFY `reserve_detail_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tour`
--
ALTER TABLE `tour`
  MODIFY `tour_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `tour_schedule`
--
ALTER TABLE `tour_schedule`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT cho bảng `transport`
--
ALTER TABLE `transport`
  MODIFY `transport_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT cho bảng `transport_detail`
--
ALTER TABLE `transport_detail`
  MODIFY `transport_detail_id` int(11) NOT NULL AUTO_INCREMENT;

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
