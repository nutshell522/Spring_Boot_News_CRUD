USE [demo_news]
GO
/****** Object:  Table [dbo].[news]    Script Date: 2023/10/18 下午 06:28:06 ******/
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
/****** Object:  Table [dbo].[news_category]    Script Date: 2023/10/18 下午 06:28:06 ******/
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
SET IDENTITY_INSERT [dbo].[news] ON 

INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (1, N'原神', NULL, NULL, NULL, NULL, CAST(N'2023-10-18T12:02:19.387' AS DateTime), CAST(N'2023-10-18T12:13:14.633' AS DateTime), 0)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (2, N'原神啟動', NULL, NULL, NULL, NULL, CAST(N'2023-10-18T12:03:30.640' AS DateTime), CAST(N'2023-10-18T12:11:59.903' AS DateTime), 0)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (3, N'原神啟動', NULL, NULL, NULL, NULL, CAST(N'2023-10-18T12:04:16.320' AS DateTime), CAST(N'2023-10-18T12:13:23.170' AS DateTime), 0)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (4, N'原神啟動', NULL, NULL, NULL, NULL, CAST(N'2023-10-18T12:07:00.053' AS DateTime), CAST(N'2023-10-18T12:13:27.523' AS DateTime), 0)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (5, N'肚子餓', N'肚子好餓好餓', 3, NULL, NULL, CAST(N'2023-10-18T12:09:39.477' AS DateTime), CAST(N'2023-10-18T12:23:51.713' AS DateTime), 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (7, N'1', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:09.903' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (8, N'2', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:16.120' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (9, N'3', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:19.357' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (10, N'4', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:22.330' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (11, N'5', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:24.793' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (12, N'6', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:29.877' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (13, N'7', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:35.063' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (14, N'8', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:38.723' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (15, N'9', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:42.463' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (16, N'10', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:45.730' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (17, N'11', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:49.613' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (18, N'12', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:53.977' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (19, N'13', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:42:58.417' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (20, N'14', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:43:01.543' AS DateTime), NULL, 1)
INSERT [dbo].[news] ([id], [topic], [content], [fk_category_id], [publish_date], [author_name], [created_at], [updated_at], [status]) VALUES (21, N'15', N'我是內容', 1, NULL, NULL, CAST(N'2023-10-18T15:43:04.500' AS DateTime), NULL, 1)
SET IDENTITY_INSERT [dbo].[news] OFF
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
