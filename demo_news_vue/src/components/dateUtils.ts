export const formatTimestamp = (timestamp: Date): string => {
    const options: Intl.DateTimeFormatOptions = {
        year: 'numeric',
        month: 'long',
        day: 'numeric',
        hour: 'numeric',
        minute: 'numeric',
    };

    if (!(timestamp instanceof Date)) {
        timestamp = new Date(timestamp);
    }

    return timestamp.toLocaleDateString(undefined, options);
};

