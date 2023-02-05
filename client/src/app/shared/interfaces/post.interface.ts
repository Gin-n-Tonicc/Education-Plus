export interface IPost {
    id: number;
    description: string;
    category: string;
    interests: string;
    ageGroup: string;
    requirements: string;
    deadlineToParticipate: Date;
    linkToApplicationForm: string;
    businessId: number;
    place: string;
    startDate: Date;
    endDate: Date;
}
