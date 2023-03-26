interface Board {
  boardContent: string;
  boardImgUrl?: string | null;
  boardNumber: number;
  boardTitle: string;
  boardWriteDatetime: string;
  commentContent: string;
  likeCount: number;
  viewCount: number;
  writerEmail: string;
  writerNickname: string;
  writerProfileUrl: string;
}

export default Board;