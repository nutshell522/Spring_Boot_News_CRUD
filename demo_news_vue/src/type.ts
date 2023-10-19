export class ApiResponse<T> {
    success: boolean;
    message: string;
    data: T;

    constructor(success: boolean, message: string, data: T) {
        this.success = success;
        this.message = message;
        this.data = data;
    }
}

export class NewsCategory {
    id: number = 0;
    name: string = '';
    description: string = '';
    createdAt: Date | null = null;
    updatedAt: Date | null = null;
    status: boolean | null = null;

    constructor(data?: Partial<NewsCategory>) {
        if (data) {
            Object.assign(this, data);
        }
    }
}

export class News {
    id: number = 0;
    topic: string = '';
    content: string = '';
    categoryId: number | null = null;
    categoryName: string = '';
    publishDate: Date | null = null;
    authorName: string = '';
    createdAt: Date | null = null;
    updatedAt: Date | null = null;
    status: boolean | null = null;
    shortContent: string = '';

    constructor(data?: Partial<News>) {
        if (data) {
            Object.assign(this, data);
        }
    }
}

