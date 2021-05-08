
USE [QLVEXE1]
GO
/****** Object:  Table [dbo].[CaLamViec]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CaLamViec](
	[MaCa] [nchar](10) NOT NULL,
	[MaChuyenXe] [nvarchar](50) NOT NULL,
	[MaNV] [nvarchar](50) NOT NULL,
	[BienSoXe] [nvarchar](50) NOT NULL,
	[ThoiGianKhoiHanh] [nvarchar](50) NULL,
	[ThoiGianDen] [varchar](50) NULL,
 CONSTRAINT [PK_PhanCong] PRIMARY KEY CLUSTERED 
(
	[MaCa] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChucVu]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChucVu](
	[ChucVu] [nvarchar](50) NOT NULL,
	[Luong] [float] NULL,
 CONSTRAINT [PK_ChucVu] PRIMARY KEY CLUSTERED 
(
	[ChucVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChuyenXe]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChuyenXe](
	[MaChuyenXe] [nvarchar](50) NOT NULL,
	[MaTuyen] [nvarchar](50) NULL,
	[TongSoChoDat] [int] NULL,
	[Ngay] [nvarchar](50) NULL,
	[TrangThaiDi] [bit] NULL,
 CONSTRAINT [PK_ChuyenXe] PRIMARY KEY CLUSTERED 
(
	[MaChuyenXe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[SDT] [nvarchar](50) NOT NULL,
	[TenKhachHang] [nvarchar](50) NULL,
	[ngaySinh] [date] NULL,
	[diaChi] [nvarchar](50) NULL,
	[TaiKhoan] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_KhachHang] PRIMARY KEY CLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiTaiKhoan]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiTaiKhoan](
	[MaLoaiTaiKhoan] [nvarchar](50) NOT NULL,
	[TenLoaiTaiKhoan] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_LoaiTaiKhoan] PRIMARY KEY CLUSTERED 
(
	[MaLoaiTaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiVe]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiVe](
	[MaLoaiVe] [nvarchar](50) NOT NULL,
	[TenLoaiVe] [nvarchar](50) NULL,
	[Gia] [float] NULL,
 CONSTRAINT [PK_LoaiVe] PRIMARY KEY CLUSTERED 
(
	[MaLoaiVe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[MaNV] [nvarchar](50) NOT NULL,
	[TenNV] [nvarchar](50) NULL,
	[NgaySinh] [nvarchar](50) NULL,
	[DiaChi] [nvarchar](50) NULL,
	[ChucVu] [nvarchar](50) NULL,
 CONSTRAINT [PK_NhanVien] PRIMARY KEY CLUSTERED 
(
	[MaNV] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TaiKhoan]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TaiKhoan](
	[TaiKhoan] [nvarchar](50) NOT NULL,
	[MatKhau] [nvarchar](50) NULL,
	[MaLoaiTaiKhoan] [nvarchar](50) NULL,
	[SDT] [nvarchar](50) NULL,
 CONSTRAINT [PK_TaiKhoan] PRIMARY KEY CLUSTERED 
(
	[TaiKhoan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tram]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tram](
	[MaTram] [nvarchar](50) NOT NULL,
	[TenTram] [nvarchar](50) NULL,
 CONSTRAINT [PK_Tram] PRIMARY KEY CLUSTERED 
(
	[MaTram] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Tuyen]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Tuyen](
	[MaTuyen] [nvarchar](50) NOT NULL,
	[TenTuyen] [nvarchar](50) NULL,
 CONSTRAINT [PK_Tuyen] PRIMARY KEY CLUSTERED 
(
	[MaTuyen] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[TuyenTram]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[TuyenTram](
	[Tuyen] [nvarchar](50) NOT NULL,
	[Tram] [nvarchar](50) NOT NULL,
	[ThoiGianTuyenDenTram] [int] NULL,
 CONSTRAINT [PK_TuyenTram] PRIMARY KEY CLUSTERED 
(
	[Tuyen] ASC,
	[Tram] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Ve]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Ve](
	[MaVe] [nvarchar](50) NOT NULL,
	[MaChuyenXe] [nvarchar](50) NULL,
	[SoChoDat] [int] NULL,
	[MaLoaiVe] [nvarchar](50) NULL,
	[NoiDi] [nvarchar](50) NULL,
	[NoiDen] [nvarchar](50) NULL,
	[TaiKhoan] [nvarchar](50) NULL,
 CONSTRAINT [PK_Ve] PRIMARY KEY CLUSTERED 
(
	[MaVe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Xe]    Script Date: 23/04/2021 1:19:12 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Xe](
	[BienSoXe] [nvarchar](50) NOT NULL,
 CONSTRAINT [PK_Xe] PRIMARY KEY CLUSTERED 
(
	[BienSoXe] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[CaLamViec] ([MaCa], [MaChuyenXe], [MaNV], [BienSoXe], [ThoiGianKhoiHanh], [ThoiGianDen]) VALUES (N'234       ', N'060421-7h-55-1', N'41', N'66l19980', N'7h', N'9h')
INSERT [dbo].[CaLamViec] ([MaCa], [MaChuyenXe], [MaNV], [BienSoXe], [ThoiGianKhoiHanh], [ThoiGianDen]) VALUES (N'wqe       ', N'080421-9h-56-1', N'41', N'66l19980', N'9h', N'11h')
GO
INSERT [dbo].[ChucVu] ([ChucVu], [Luong]) VALUES (N'Kiem ve', 5000000)
INSERT [dbo].[ChucVu] ([ChucVu], [Luong]) VALUES (N'Lai xe', 7000000)
GO
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'060421-7h-55-1', N'55', 2, N'2021-04-06', 1)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'080421-9h-56-1', N'56', 2, N'2021-04-08', 1)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'170421-11h-56-0', N'56', 2, N'2021-04-17', 0)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'170421-15h-57-0', N'57', 1, N'2021-04-17', 0)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'180421-17h-56-1', N'56', 1, N'2021-04-18', 1)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'180421-7h-56-0', N'56', 2, N'2021-04-18', 0)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'200421-5h-56-1', N'56', 2, N'2021-04-20', 1)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'220421-5h-55-1', N'55', 3, N'2021-04-22', 1)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'220421-5h-56-0', N'56', 3, N'2021-04-22', 0)
INSERT [dbo].[ChuyenXe] ([MaChuyenXe], [MaTuyen], [TongSoChoDat], [Ngay], [TrangThaiDi]) VALUES (N'220421-5h-56-1', N'56', 2, N'2021-04-22', 1)
GO
INSERT [dbo].[KhachHang] ([SDT], [TenKhachHang], [ngaySinh], [diaChi], [TaiKhoan]) VALUES (N'0484684', N'Nguyen Minh Huan', CAST(N'2021-04-01' AS Date), N'dsfds', N'minhhuan')
INSERT [dbo].[KhachHang] ([SDT], [TenKhachHang], [ngaySinh], [diaChi], [TaiKhoan]) VALUES (N'4464', N'Vu Tan Duc', CAST(N'2000-01-01' AS Date), N'dsfs', N'tanduc')
INSERT [dbo].[KhachHang] ([SDT], [TenKhachHang], [ngaySinh], [diaChi], [TaiKhoan]) VALUES (N'44654', N'Nguyen Huu Nhan', CAST(N'2000-08-18' AS Date), N'fsdfds', N'huunhan')
INSERT [dbo].[KhachHang] ([SDT], [TenKhachHang], [ngaySinh], [diaChi], [TaiKhoan]) VALUES (N'5454', N'Trung Hien', CAST(N'2000-01-01' AS Date), N'hjgguy', N'hien')
INSERT [dbo].[KhachHang] ([SDT], [TenKhachHang], [ngaySinh], [diaChi], [TaiKhoan]) VALUES (N'56546', N'Nguyen Duc Tu', CAST(N'2000-11-20' AS Date), N'cscsd', N'ductu')
INSERT [dbo].[KhachHang] ([SDT], [TenKhachHang], [ngaySinh], [diaChi], [TaiKhoan]) VALUES (N'65468', N'Nguyen Thi Admin', CAST(N'2000-01-01' AS Date), N'uuhu', N'admin')
GO
INSERT [dbo].[LoaiTaiKhoan] ([MaLoaiTaiKhoan], [TenLoaiTaiKhoan]) VALUES (N'AD', N'Admin')
INSERT [dbo].[LoaiTaiKhoan] ([MaLoaiTaiKhoan], [TenLoaiTaiKhoan]) VALUES (N'KH', N'Khách Hàng')
GO
INSERT [dbo].[LoaiVe] ([MaLoaiVe], [TenLoaiVe], [Gia]) VALUES (N'VSV', N'Ve sinh vien', 3000)
INSERT [dbo].[LoaiVe] ([MaLoaiVe], [TenLoaiVe], [Gia]) VALUES (N'VT', N'Ve thuong', 7000)
GO
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [NgaySinh], [DiaChi], [ChucVu]) VALUES (N'41', N'41', N'1970-01-01', N'hcm', N'Lai xe')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [NgaySinh], [DiaChi], [ChucVu]) VALUES (N'42', N'Nguyen Van Teo', N'2000-08-18', N'hn', N'Lai xe')
INSERT [dbo].[NhanVien] ([MaNV], [TenNV], [NgaySinh], [DiaChi], [ChucVu]) VALUES (N'43', N'Nguyen Huu Ti', N'1999-09-09', N'can tho', N'Lai xe')
GO
INSERT [dbo].[TaiKhoan] ([TaiKhoan], [MatKhau], [MaLoaiTaiKhoan], [SDT]) VALUES (N'admin', N'admin', N'AD', N'65468')
INSERT [dbo].[TaiKhoan] ([TaiKhoan], [MatKhau], [MaLoaiTaiKhoan], [SDT]) VALUES (N'ductu', N'1234', N'KH', N'56546')
INSERT [dbo].[TaiKhoan] ([TaiKhoan], [MatKhau], [MaLoaiTaiKhoan], [SDT]) VALUES (N'hien', N'1234', N'KH', N'5454')
INSERT [dbo].[TaiKhoan] ([TaiKhoan], [MatKhau], [MaLoaiTaiKhoan], [SDT]) VALUES (N'huunhan', N'1234', N'KH', N'44654')
INSERT [dbo].[TaiKhoan] ([TaiKhoan], [MatKhau], [MaLoaiTaiKhoan], [SDT]) VALUES (N'minhhuan', N'1234', N'KH', N'0484684')
INSERT [dbo].[TaiKhoan] ([TaiKhoan], [MatKhau], [MaLoaiTaiKhoan], [SDT]) VALUES (N'tanduc', N'1234', N'KH', N'4464')
GO
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram1', N'tram1')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram10', N'tram10')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram11', N'tram11')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram2', N'tram2')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram20', N'tram20')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram3', N'tram3')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram4', N'tram4')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram5', N'tram5')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram6', N'tram6')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram7', N'tram7')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram8', N'tram8')
INSERT [dbo].[Tram] ([MaTram], [TenTram]) VALUES (N'tram9', N'tram9')
GO
INSERT [dbo].[Tuyen] ([MaTuyen], [TenTuyen]) VALUES (N'55', N'Tuyen 55')
INSERT [dbo].[Tuyen] ([MaTuyen], [TenTuyen]) VALUES (N'56', N'Tuyen 56')
INSERT [dbo].[Tuyen] ([MaTuyen], [TenTuyen]) VALUES (N'57', N'Tuyen 57')
INSERT [dbo].[Tuyen] ([MaTuyen], [TenTuyen]) VALUES (N'58', N'Tuyen58')
INSERT [dbo].[Tuyen] ([MaTuyen], [TenTuyen]) VALUES (N'59', N'Tuyen 59')
GO
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'55', N'tram1', 0)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'55', N'tram2', 30)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'55', N'tram3', 60)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'56', N'tram2', 0)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'56', N'tram3', 15)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'56', N'tram4', 45)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'57', N'tram4', 0)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'57', N'tram5', 10)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'57', N'tram6', 20)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'58', N'tram10', 20)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'58', N'tram7', 0)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'58', N'tram8', 10)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'59', N'tram2', 0)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'59', N'tram3', 20)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'59', N'tram4', 40)
INSERT [dbo].[TuyenTram] ([Tuyen], [Tram], [ThoiGianTuyenDenTram]) VALUES (N'59', N'tram5', 60)
GO
INSERT [dbo].[Ve] ([MaVe], [MaChuyenXe], [SoChoDat], [MaLoaiVe], [NoiDi], [NoiDen], [TaiKhoan]) VALUES (N'4', N'170421-11h-56-0', 2, N'VT', N'tram4', N'tram2', N'huunhan')
INSERT [dbo].[Ve] ([MaVe], [MaChuyenXe], [SoChoDat], [MaLoaiVe], [NoiDi], [NoiDen], [TaiKhoan]) VALUES (N'5', N'180421-7h-56-0', 2, N'VT', N'tram4', N'tram2', N'huunhan')
INSERT [dbo].[Ve] ([MaVe], [MaChuyenXe], [SoChoDat], [MaLoaiVe], [NoiDi], [NoiDen], [TaiKhoan]) VALUES (N'6', N'220421-5h-55-1', 3, N'VT', N'tram1', N'tram2', N'huunhan')
INSERT [dbo].[Ve] ([MaVe], [MaChuyenXe], [SoChoDat], [MaLoaiVe], [NoiDi], [NoiDen], [TaiKhoan]) VALUES (N'7', N'200421-5h-56-1', 2, N'VSV', N'tram2', N'tram3', N'huunhan')
INSERT [dbo].[Ve] ([MaVe], [MaChuyenXe], [SoChoDat], [MaLoaiVe], [NoiDi], [NoiDen], [TaiKhoan]) VALUES (N'8', N'220421-5h-56-1', 2, N'VSV', N'tram2', N'tram3', N'huunhan')
INSERT [dbo].[Ve] ([MaVe], [MaChuyenXe], [SoChoDat], [MaLoaiVe], [NoiDi], [NoiDen], [TaiKhoan]) VALUES (N'9', N'220421-5h-56-0', 3, N'VSV', N'tram3', N'tram2', N'huunhan')
GO
INSERT [dbo].[Xe] ([BienSoXe]) VALUES (N'66l19980')
INSERT [dbo].[Xe] ([BienSoXe]) VALUES (N'66l19981')
INSERT [dbo].[Xe] ([BienSoXe]) VALUES (N'66l19982')
INSERT [dbo].[Xe] ([BienSoXe]) VALUES (N'66l19983')
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [Unique_TaiKhoan]    Script Date: 23/04/2021 1:19:13 PM ******/
ALTER TABLE [dbo].[TaiKhoan] ADD  CONSTRAINT [Unique_TaiKhoan] UNIQUE NONCLUSTERED 
(
	[SDT] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[CaLamViec]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_ChuyenXe] FOREIGN KEY([MaChuyenXe])
REFERENCES [dbo].[ChuyenXe] ([MaChuyenXe])
ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[CaLamViec] CHECK CONSTRAINT [FK_PhanCong_ChuyenXe]
GO
ALTER TABLE [dbo].[CaLamViec]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_NhanVien] FOREIGN KEY([MaNV])
REFERENCES [dbo].[NhanVien] ([MaNV])
GO
ALTER TABLE [dbo].[CaLamViec] CHECK CONSTRAINT [FK_PhanCong_NhanVien]
GO
ALTER TABLE [dbo].[CaLamViec]  WITH CHECK ADD  CONSTRAINT [FK_PhanCong_Xe] FOREIGN KEY([BienSoXe])
REFERENCES [dbo].[Xe] ([BienSoXe])
GO
ALTER TABLE [dbo].[CaLamViec] CHECK CONSTRAINT [FK_PhanCong_Xe]
GO
ALTER TABLE [dbo].[ChuyenXe]  WITH CHECK ADD  CONSTRAINT [FK_ChuyenXe_Tuyen] FOREIGN KEY([MaTuyen])
REFERENCES [dbo].[Tuyen] ([MaTuyen])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[ChuyenXe] CHECK CONSTRAINT [FK_ChuyenXe_Tuyen]
GO
ALTER TABLE [dbo].[NhanVien]  WITH CHECK ADD  CONSTRAINT [FK_NhanVien_ChucVu] FOREIGN KEY([ChucVu])
REFERENCES [dbo].[ChucVu] ([ChucVu])
GO
ALTER TABLE [dbo].[NhanVien] CHECK CONSTRAINT [FK_NhanVien_ChucVu]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_KhachHang] FOREIGN KEY([SDT])
REFERENCES [dbo].[KhachHang] ([SDT])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_KhachHang]
GO
ALTER TABLE [dbo].[TaiKhoan]  WITH CHECK ADD  CONSTRAINT [FK_TaiKhoan_LoaiTaiKhoan] FOREIGN KEY([MaLoaiTaiKhoan])
REFERENCES [dbo].[LoaiTaiKhoan] ([MaLoaiTaiKhoan])
GO
ALTER TABLE [dbo].[TaiKhoan] CHECK CONSTRAINT [FK_TaiKhoan_LoaiTaiKhoan]
GO
ALTER TABLE [dbo].[TuyenTram]  WITH CHECK ADD  CONSTRAINT [FK_TuyenTram_Tram] FOREIGN KEY([Tram])
REFERENCES [dbo].[Tram] ([MaTram])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TuyenTram] CHECK CONSTRAINT [FK_TuyenTram_Tram]
GO
ALTER TABLE [dbo].[TuyenTram]  WITH CHECK ADD  CONSTRAINT [FK_TuyenTram_Tuyen] FOREIGN KEY([Tuyen])
REFERENCES [dbo].[Tuyen] ([MaTuyen])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[TuyenTram] CHECK CONSTRAINT [FK_TuyenTram_Tuyen]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_ChuyenXe] FOREIGN KEY([MaChuyenXe])
REFERENCES [dbo].[ChuyenXe] ([MaChuyenXe])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_ChuyenXe]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_LoaiVe] FOREIGN KEY([MaLoaiVe])
REFERENCES [dbo].[LoaiVe] ([MaLoaiVe])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_LoaiVe]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_TaiKhoan] FOREIGN KEY([TaiKhoan])
REFERENCES [dbo].[TaiKhoan] ([TaiKhoan])
ON UPDATE CASCADE
ON DELETE CASCADE
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_TaiKhoan]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_TramNoiDen] FOREIGN KEY([NoiDen])
REFERENCES [dbo].[Tram] ([MaTram])
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_TramNoiDen]
GO
ALTER TABLE [dbo].[Ve]  WITH CHECK ADD  CONSTRAINT [FK_Ve_TramNoiDi] FOREIGN KEY([NoiDi])
REFERENCES [dbo].[Tram] ([MaTram])
GO
ALTER TABLE [dbo].[Ve] CHECK CONSTRAINT [FK_Ve_TramNoiDi]
GO
