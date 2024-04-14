-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 14, 2024 lúc 09:23 PM
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
  `invoiceid` varchar(100) NOT NULL,
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
('hd00005', '2024-04-05', 1670.000),
('hd240406142341', '2024-04-06', 4170.000),
('hd240406142814', '2024-04-06', 2700.000),
('hd240406151605', '2024-04-06', 4305.000),
('hd240406152335', '2024-04-06', 4305.000),
('hd240406181632', '2024-04-06', 1530.000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `invoice_detail`
--

CREATE TABLE `invoice_detail` (
  `invoiceid` varchar(100) NOT NULL,
  `productid` varchar(10) NOT NULL,
  `productname` varchar(255) DEFAULT NULL,
  `promotionrate` decimal(5,2) DEFAULT NULL,
  `quantitybuy` int(11) NOT NULL,
  `price` decimal(10,3) DEFAULT NULL,
  `createdate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `invoice_detail`
--

INSERT INTO `invoice_detail` (`invoiceid`, `productid`, `productname`, `promotionrate`, `quantitybuy`, `price`, `createdate`) VALUES
('hd00001', 'sp00003', 'Laptop Dell XPS 13', 0.10, 1, 1350.000, '2024-04-01'),
('hd00001', 'sp00004', 'Smartphone Samsung Galaxy S21', 0.00, 1, 1200.000, '2024-04-01'),
('hd00002', 'sp00004', 'Smartphone Samsung Galaxy S21', 0.20, 1, 960.000, '2024-04-02'),
('hd00002', 'sp00005', 'Smart TV LG 55-inch 4K', 0.00, 1, 900.000, '2024-04-02'),
('hd00003', 'sp00003', 'Laptop Dell XPS 13', 0.10, 1, 1350.000, '2024-04-03'),
('hd00003', 'sp00005', 'Smart TV LG 55-inch 4K', 0.10, 1, 810.000, '2024-04-03'),
('hd00003', 'sp00006', 'Wireless Headphones Sony WH-1000XM4', 0.00, 1, 300.000, '2024-04-03'),
('hd00004', 'sp00003', 'Laptop Dell XPS 13', 0.00, 1, 1500.000, '2024-04-04'),
('hd00004', 'sp00004', 'Smartphone Samsung Galaxy S21', 0.00, 1, 1200.000, '2024-04-04'),
('hd00004', 'sp00006', 'Wireless Headphones Sony WH-1000XM4', 0.10, 1, 270.000, '2024-04-04'),
('hd00005', 'sp00005', 'Smart TV LG 55-inch 4K', 0.00, 1, 900.000, '2024-04-05'),
('hd00005', 'sp00006', 'Wireless Headphones Sony WH-1000XM4', 0.10, 1, 270.000, '2024-04-05'),
('hd00005', 'sp00007', 'Gaming Console Sony PlayStation 5', 0.00, 1, 500.000, '2024-04-05'),
('hd240406142341', 'sp00001', 'Sản phẩm 1', 0.10, 3, 90.000, '2024-04-06'),
('hd240406142341', 'sp00003', 'Laptop Dell XPS 13', 0.10, 2, 1350.000, '2024-04-06'),
('hd240406142341', 'sp00004', 'Smartphone Samsung Galaxy S21', 0.00, 1, 1200.000, '2024-04-06'),
('hd240406142814', 'sp00003', 'Laptop Dell XPS 13', 0.10, 2, 1350.000, '2024-04-06'),
('hd240406151605', 'sp00002', 'Sản phẩm 2', 0.15, 2, 127.500, '2024-04-06'),
('hd240406151605', 'sp00003', 'Laptop Dell XPS 13', 0.10, 3, 1350.000, '2024-04-06'),
('hd240406152335', 'sp00002', 'Sản phẩm 2', 0.15, 2, 127.500, '2024-04-06'),
('hd240406152335', 'sp00003', 'Laptop Dell XPS 13', 0.10, 3, 1350.000, '2024-04-06'),
('hd240406181632', 'sp00001', 'Sản phẩm 1', 0.10, 2, 90.000, '2024-04-06'),
('hd240406181632', 'sp00003', 'Laptop Dell XPS 13', 0.10, 1, 1350.000, '2024-04-06');

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
('sp00001', 'Sản phẩm 1', 100.000, 10, 'Mô tả sản phẩm 1', 's00001'),
('sp00002', 'Sản phẩm 2', 150.000, 27, 'Mô tả sản phẩm 2', 's00002'),
('sp00003', 'Laptop Dell XPS 13', 1500.000, 69, 'Ultra-portable laptop with stunning display', 's00003'),
('sp00004', 'Smartphone Samsung Galaxy S21', 1200.000, 17, 'Flagship smartphone with high-performance camera', 's00004'),
('sp00005', 'Smart TV LG 55-inch 4K', 900.000, 20, 'Immersive viewing experience with 4K resolution', 's00003'),
('sp00006', 'Wireless Headphones Sony WH-1000XM4', 300.000, 25, 'Noise-canceling headphones with premium sound quality', 's00004'),
('sp00007', 'Gaming Console Sony PlayStation 5', 500.000, 30, 'Next-generation gaming console for immersive gameplay', 's00003'),
('sp00008', 'Iphone 15 Pro Max', 250000.000, 14, 'Điện thoại thông minh', 's00003'),
('sp00009', 'Iphone 14 Pro Max', 14000.000, 33, 'Điện thoại thông minh của Apple', 's00003'),
('sp00010', 'Iphone 13 Pro Max', 190000.000, 36, 'Điện thoại thông minh của hãng Apple', 's00003');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `promotion`
--

CREATE TABLE `promotion` (
  `productid` varchar(255) NOT NULL,
  `supplierid` varchar(255) NOT NULL,
  `promotionrate` double NOT NULL,
  `startdate` date NOT NULL,
  `enddate` date NOT NULL,
  `createddate` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `promotion`
--

INSERT INTO `promotion` (`productid`, `supplierid`, `promotionrate`, `startdate`, `enddate`, `createddate`) VALUES
('sp00005', 's00003', 0.1, '2024-04-18', '2024-04-19', '15040020182024'),
('sp00005', 's00003', 0.3, '2024-04-20', '2024-04-21', '15040125372024'),
('sp00005', 's00003', 0.15, '2024-04-16', '2024-04-17', '15040129322024');

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
-- Chỉ mục cho bảng `invoice_detail`
--
ALTER TABLE `invoice_detail`
  ADD PRIMARY KEY (`invoiceid`,`productid`),
  ADD KEY `productid` (`productid`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`productid`),
  ADD KEY `supplierid` (`supplierid`);

--
-- Chỉ mục cho bảng `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`createddate`),
  ADD KEY `fk_product` (`productid`),
  ADD KEY `fk_supplier` (`supplierid`);

--
-- Chỉ mục cho bảng `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`supplierid`);

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `invoice_detail`
--
ALTER TABLE `invoice_detail`
  ADD CONSTRAINT `invoice_detail_ibfk_1` FOREIGN KEY (`invoiceid`) REFERENCES `invoice` (`invoiceid`),
  ADD CONSTRAINT `invoice_detail_ibfk_2` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`);

--
-- Các ràng buộc cho bảng `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `product_ibfk_1` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierid`);

--
-- Các ràng buộc cho bảng `promotion`
--
ALTER TABLE `promotion`
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`productid`) REFERENCES `product` (`productid`),
  ADD CONSTRAINT `fk_supplier` FOREIGN KEY (`supplierid`) REFERENCES `supplier` (`supplierid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
