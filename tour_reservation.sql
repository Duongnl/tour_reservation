-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th9 26, 2024 lúc 11:04 AM
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
  `phone_number` int(11) DEFAULT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL
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
  `customer_type` int(11) NOT NULL,
  `sex` int(11) DEFAULT NULL,
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
  `birthday` date DEFAULT NULL
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
  `percentage` int(11) NOT NULL,
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
  `price` int(11) DEFAULT NULL,
  `time` datetime NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `reserve_detail`
--

CREATE TABLE `reserve_detail` (
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
  `city` varchar(255) NOT NULL,
  `country` varchar(255) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tour_schedule`
--

CREATE TABLE `tour_schedule` (
  `schedule_id` int(11) NOT NULL,
  `tour_id` int(11) NOT NULL,
  `promotion_id` int(11) DEFAULT NULL,
  `schedule_name` varchar(255) NOT NULL,
  `departure_time` datetime DEFAULT NULL,
  `return_time` datetime DEFAULT NULL,
  `quantity` int(11) NOT NULL,
  `price_adult` int(11) DEFAULT NULL,
  `price_child` int(11) DEFAULT NULL,
  `visa_expire` date DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transport`
--

CREATE TABLE `transport` (
  `transport_id` int(11) NOT NULL,
  `transport_name` varchar(255) NOT NULL,
  `transport_detail` varchar(255) DEFAULT NULL,
  `departure_time` datetime DEFAULT NULL,
  `departure_location` varchar(255) DEFAULT NULL,
  `destination_location` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `transport_detail`
--

CREATE TABLE `transport_detail` (
  `schedule_id` int(11) NOT NULL,
  `transport_id` int(11) NOT NULL,
  `direction` varchar(255) NOT NULL
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
  ADD UNIQUE KEY `phone_number` (`phone_number`),
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
  ADD PRIMARY KEY (`reserve_id`,`customer_id`),
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
  ADD PRIMARY KEY (`schedule_id`,`transport_id`),
  ADD KEY `transport_detail_fk1` (`transport_id`),
  ADD KEY `transport_detail_fk0` (`schedule_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(11) NOT NULL AUTO_INCREMENT;

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
-- AUTO_INCREMENT cho bảng `tour`
--
ALTER TABLE `tour`
  MODIFY `tour_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `tour_schedule`
--
ALTER TABLE `tour_schedule`
  MODIFY `schedule_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `transport`
--
ALTER TABLE `transport`
  MODIFY `transport_id` int(11) NOT NULL AUTO_INCREMENT;

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
