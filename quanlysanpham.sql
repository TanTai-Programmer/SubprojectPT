-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 04, 2024 lúc 12:09 PM
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
-- Cơ sở dữ liệu: `quanlysanpham`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice`
--

CREATE TABLE `invoice` (
  `invoiceid` varchar(10) NOT NULL,
  `createdate` date DEFAULT NULL,
  `totalamount` decimal(10,3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `invoice`
--

INSERT INTO `invoice` (`invoiceid`, `createdate`, `totalamount`) VALUES
('hd00001', '2024-04-01', 2550.000),
('hd00002', '2024-04-02', 1860.000),
('hd00003', '2024-04-03', 2460.000),
('hd00004', '2024-04-04', 2970.000),
('hd00005', '2024-04-05', 1670.000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `productid` varchar(10) NOT NULL,
  `productname` varchar(255) NOT NULL,
  `price` decimal(10,3) NOT NULL,
  `quantity` int(11) NOT NULL,
  `description` text DEFAULT NULL,
  `supplierid` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`productid`, `productname`, `price`, `quantity`, `description`, `supplierid`) VALUES
('sp00001', 'Sản phẩm 1', 100.000, 50, 'Mô tả sản phẩm 1', 's00001'),
('sp00002', 'Sản phẩm 2', 150.000, 30, 'Mô tả sản phẩm 2', 's00002'),
('sp00003', 'Laptop Dell XPS 13', 1500.000, 10, 'Ultra-portable laptop with stunning display', 's00003'),
('sp00004', 'Smartphone Samsung Galaxy S21', 1200.000, 15, 'Flagship smartphone with high-performance camera', 's00004'),
('sp00005', 'Smart TV LG 55-inch 4K', 900.000, 20, 'Immersive viewing experience with 4K resolution', 's00003'),
('sp00006', 'Wireless Headphones Sony WH-1000XM4', 300.000, 25, 'Noise-canceling headphones with premium sound quality', 's00004'),
('sp00007', 'Gaming Console Sony PlayStation 5', 500.000, 30, 'Next-generation gaming console for immersive gameplay', 's00003');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion`
--

CREATE TABLE `promotion` (
  `productid` varchar(10) DEFAULT NULL,
  `supplierid` varchar(10) DEFAULT NULL,
  `promotionrate` decimal(5,2) NOT NULL,
  `startdate` date DEFAULT NULL,
  `enddate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `promotion`
--

INSERT INTO `promotion` (`productid`, `supplierid`, `promotionrate`, `startdate`, `enddate`) VALUES
('sp00001', 's00001', 0.10, '2024-04-03', '2024-04-10'),
('sp00002', 's00002', 0.15, '2024-04-04', '2024-04-11');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `supplier`
--

CREATE TABLE `supplier` (
  `supplierid` varchar(10) NOT NULL,
  `suppliername` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `supplier`
--

INSERT INTO `supplier` (`supplierid`, `suppliername`, `address`, `phonenumber`) VALUES
('s00001', 'Nhà cung cấp 1', 'Địa chỉ 1', '1234567890'),
('s00002', 'Nhà cung cấp 2', 'Địa chỉ 2', '0987654321'),
('s00003', 'Apple Inc.', '1 Infinite Loop, Cupertino, CA', '1234567890'),
('s00004', 'Samsung Electronics', '129 Samsung-ro, Maetan-dong, Yeongtong-gu, Suwon-si, Gyeonggi-do, South Korea', '0987654321');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `invoice`
--
ALTER TABLE `invoice`
  ADD PRIMARY KEY (`invoiceid`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productid`),
  ADD KEY `supplierid` (`supplierid`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplierid`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
