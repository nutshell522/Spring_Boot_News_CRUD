USE [master]
GO
/****** Object:  Database [demo_news]    Script Date: 2023/10/17 下午 06:30:15 ******/
CREATE DATABASE [demo_news]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'demo_news', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\demo_news.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'demo_news_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL16.MSSQLSERVER\MSSQL\DATA\demo_news_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT, LEDGER = OFF
GO
ALTER DATABASE [demo_news] SET COMPATIBILITY_LEVEL = 160
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [demo_news].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [demo_news] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [demo_news] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [demo_news] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [demo_news] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [demo_news] SET ARITHABORT OFF 
GO
ALTER DATABASE [demo_news] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [demo_news] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [demo_news] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [demo_news] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [demo_news] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [demo_news] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [demo_news] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [demo_news] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [demo_news] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [demo_news] SET  DISABLE_BROKER 
GO
ALTER DATABASE [demo_news] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [demo_news] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [demo_news] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [demo_news] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [demo_news] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [demo_news] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [demo_news] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [demo_news] SET RECOVERY FULL 
GO
ALTER DATABASE [demo_news] SET  MULTI_USER 
GO
ALTER DATABASE [demo_news] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [demo_news] SET DB_CHAINING OFF 
GO
ALTER DATABASE [demo_news] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [demo_news] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [demo_news] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [demo_news] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'demo_news', N'ON'
GO
ALTER DATABASE [demo_news] SET QUERY_STORE = ON
GO
ALTER DATABASE [demo_news] SET QUERY_STORE (OPERATION_MODE = READ_WRITE, CLEANUP_POLICY = (STALE_QUERY_THRESHOLD_DAYS = 30), DATA_FLUSH_INTERVAL_SECONDS = 900, INTERVAL_LENGTH_MINUTES = 60, MAX_STORAGE_SIZE_MB = 1000, QUERY_CAPTURE_MODE = AUTO, SIZE_BASED_CLEANUP_MODE = AUTO, MAX_PLANS_PER_QUERY = 200, WAIT_STATS_CAPTURE_MODE = ON)
GO
USE [demo_news]
GO
/****** Object:  User [sa5]    Script Date: 2023/10/17 下午 06:30:15 ******/
CREATE USER [sa5] FOR LOGIN [sa5] WITH DEFAULT_SCHEMA=[dbo]
GO
ALTER ROLE [db_owner] ADD MEMBER [sa5]
GO
/****** Object:  Table [dbo].[news]    Script Date: 2023/10/17 下午 06:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[topic] [nvarchar](50) NULL,
	[content] [nvarchar](max) NULL,
	[fk_category_id] [int] NULL,
	[publish_date] [datetime] NULL,
	[author_name] [nvarchar](50) NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_news] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[news_category]    Script Date: 2023/10/17 下午 06:30:15 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[news_category](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](50) NULL,
	[description] [nvarchar](max) NULL,
	[created_at] [datetime] NULL,
	[updated_at] [datetime] NULL,
	[status] [bit] NULL,
 CONSTRAINT [PK_news_category] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[news_category] ON 

INSERT [dbo].[news_category] ([id], [name], [description], [created_at], [updated_at], [status]) VALUES (1, N'蘋果', N'', CAST(N'2023-10-17T17:23:10.423' AS DateTime), NULL, 1)
INSERT [dbo].[news_category] ([id], [name], [description], [created_at], [updated_at], [status]) VALUES (2, N'豬排', N'', CAST(N'2023-10-17T17:30:04.640' AS DateTime), CAST(N'2023-10-17T17:54:17.217' AS DateTime), 0)
INSERT [dbo].[news_category] ([id], [name], [description], [created_at], [updated_at], [status]) VALUES (3, N'烤豬排', N'匈牙利豬', CAST(N'2023-10-17T17:31:01.950' AS DateTime), CAST(N'2023-10-17T18:04:04.103' AS DateTime), 1)
SET IDENTITY_INSERT [dbo].[news_category] OFF
GO
ALTER TABLE [dbo].[news] ADD  CONSTRAINT [DF_news_created_at]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[news] ADD  CONSTRAINT [DF_news_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[news_category] ADD  CONSTRAINT [DF_news_category_created_at]  DEFAULT (getdate()) FOR [created_at]
GO
ALTER TABLE [dbo].[news_category] ADD  CONSTRAINT [DF_news_category_status]  DEFAULT ((1)) FOR [status]
GO
ALTER TABLE [dbo].[news]  WITH CHECK ADD  CONSTRAINT [FK_news_news_category] FOREIGN KEY([fk_category_id])
REFERENCES [dbo].[news_category] ([id])
GO
ALTER TABLE [dbo].[news] CHECK CONSTRAINT [FK_news_news_category]
GO
USE [master]
GO
ALTER DATABASE [demo_news] SET  READ_WRITE 
GO
