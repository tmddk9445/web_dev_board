import { ChangeEvent, useEffect, useRef, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCookies } from 'react-cookie';

import axios, { AxiosResponse } from 'axios';
import { Box, Divider, Fab, IconButton, Input } from '@mui/material';
import ImageOutlinedIcon from '@mui/icons-material/ImageOutlined';
import CreateIcon from '@mui/icons-material/Create';

import { PostBoardDto } from 'src/apis/request/board';
import ResponseDto from 'src/apis/response';
import { PostBoardResponseDto } from 'src/apis/response/board';
import { authorizationHeader, FILE_UPLOAD_URL, mutipartHeader, POST_BOARD_URL } from 'src/constants/api';

export default function BoardWriteView() {

  //          Hook          //
  const navigator = useNavigate();

  const imageRef = useRef<HTMLInputElement | null>(null);

  const [cookies] = useCookies();
  const [boardTitle, setBoardTitle] = useState<string>('');
  const [boardContent, setBoardContent] = useState<string>('');
  const [boardImgUrl, setBoardImgUrl] = useState<string>('');

  const accessToken = cookies.accessToken;

  //          Event Handler          //
  const onImageUploadButtonHandler = () => {
    if (!imageRef.current) return;
    imageRef.current.click();
  }

  // TODO : BoardDetailView, BoardUpdateView, MyPageHaed에서 중복
  // TODO : Hook 또는 외부 함수로 변경 
  const onImageUploadChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
    if (!event.target.files) return;
    const data = new FormData();
    data.append('file', event.target.files[0]);
    
    axios.post(FILE_UPLOAD_URL, data, mutipartHeader())
      .then((response) => imageUploadResponseHandler(response))
      .catch((error) => imageUplloadErrorHandler(error));
  }

  const onWriteHandler = () => {
    if (!boardTitle.trim() || !boardContent.trim()) {
      alert('모든 내용을 입력해주세요.');
      return;
    }
    postBoard();
  }

  const postBoard = () => {
    const data: PostBoardDto = { boardTitle, boardContent, boardImgUrl };
    axios.post(POST_BOARD_URL, data, authorizationHeader(accessToken))
      .then((response) => postBoardResponseHandler(response))
      .catch((error) => postBoardErrorHandler(error));
  }

  //          Response Handler          //
  const postBoardResponseHandler = (response: AxiosResponse<any, any>) => {
    const { result, message, data } = response.data as ResponseDto<PostBoardResponseDto>;
    if (!result || !data) {
      alert(message);
      return;
    }
    navigator('/myPage');
  }

  const imageUploadResponseHandler = (response: AxiosResponse<any, any>) => {
    const imageUrl = response.data as string;
    if (!imageUrl) return;
    setBoardImgUrl(imageUrl);
  }

  //          Error Handler          //
  const postBoardErrorHandler = (error: any) => {
    console.log(error.message);
  }

  const imageUplloadErrorHandler = (error: any) => {
    console.log(error.message);
  }

  //          Use Effect          //
  useEffect(() => {
    if (!accessToken) {
      alert('로그인이 필요한 작업입니다.');
      navigator('/auth');
    }
  }, []);

  return (
    <Box sx={{ p: '0px 198px', backgroundColor: 'rgba(0, 0, 0, 0.05)' }}>
      <Box sx={{ p: '100px 24px', backgroundColor: '#ffffff' }}>
        <Input fullWidth disableUnderline placeholder='제목을 입력하세요.' sx={{ fontSize: '32px', fontWeight: 500 }} onChange={(event) => setBoardTitle(event.target.value)} />
        <Divider sx={{ m: '40px 0px' }} />
        <Box sx={{ display: 'flex', alignItems: 'start' }}>

          <Box sx={{ width: '100%' }}>
            <Input fullWidth disableUnderline multiline minRows={5} placeholder='본문을 작성해주세요.' sx={{ fontSize: '18px', fontWeight: 500, lineHeight: '150%' }} onChange={(event) => setBoardContent(event.target.value)}/>
            <Box sx={{ width: '100%' }} component='img' src={boardImgUrl} />
          </Box>

          <IconButton onClick={() => onImageUploadButtonHandler()}>
            <ImageOutlinedIcon />
            <input ref={imageRef} hidden type='file' accept='image/*' onChange={(event) => onImageUploadChangeHandler(event)}/>
          </IconButton>

        </Box>
      </Box>
      <Fab sx={{ position: 'fixed', bottom: '200px', right: '248px', backgroundColor: '#999999' }} onClick={onWriteHandler}>
        <CreateIcon />
      </Fab>
    </Box>
  )
}
