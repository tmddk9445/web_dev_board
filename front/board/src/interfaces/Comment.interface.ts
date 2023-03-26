interface Comment {
  commentNumber: number;
  writerEmail: string;
  boardNumber: number;
  writeDatetime: string;
  commentContent: string;
  writerProfileUrl?: string | null;
}

export default Comment;