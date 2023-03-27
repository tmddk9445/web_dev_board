import { Dispatch, SetStateAction, useState } from 'react'
import axios from 'axios';
import { Box, TextField, Typography, FormControl, InputLabel, Input, InputAdornment, IconButton, Button } from '@mui/material';
import VisibilityOff from '@mui/icons-material/VisibilityOff';
import Visibility from '@mui/icons-material/Visibility';

import { USER } from 'src/mock';
import { useUserStore } from 'src/stores';
import { useNavigate } from 'react-router-dom';
import { SIGN_IN_URL } from 'src/constants/api';
import ResponseDto from 'src/apis/response';
import { SignInResponseDto } from 'src/apis/response/auth';
import { SignInDto } from 'src/apis/request/auth';
import { useCookies } from 'react-cookie';

interface Props {
    setLoginView: Dispatch<SetStateAction<boolean>>
}

export default function LoginCardView({ setLoginView }: Props) {

  const [cookies, setCookies] = useCookies();

  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [showPassword, setShowPassword] = useState<boolean>(false);
  
  const { setUser } = useUserStore();

  const navigator = useNavigate();

  const onLoginHandler = () => {
    //? email 입력했는지 검증 / password 입력했는지 검증
    if (!email.trim() || !password) {
        alert('모든 값을 입력해주세요.');
        return;
    }

    const data: SignInDto = { email, password };

    axios.post(SIGN_IN_URL, data)
    .then((response) => {
        const { result, message, data} = response.data as ResponseDto<SignInResponseDto>;
        if (result && data) {
          const { token, expiredTime, ...user  } = data;
          //? 로그인 처리
          //? 쿠키에 로그인 데이터 (Token) 보관
          const now = new Date().getTime();
          const expires = new Date(now + expiredTime);
          setCookies('accessToken', token, { expires })
          //? 스토어에 유저 데이터 보관
          setUser(user);
          navigator("/");
        } else {
          alert("로그인 정보가 잘못되었습니다.");
        }
    })
    .catch((error) => {
        console.log(error.message);
    })


  }

  return (
    <Box display='flex' sx={{height: '100%', flexDirection: 'column', justifyContent: 'space-between'}}>
        <Box>
            <Typography variant='h5' fontWeight='900'>로그인</Typography>
            <TextField sx={{mt: '40px'}} fullWidth label="이메일 주소" variant="standard" onChange={(event) => setEmail(event.target.value)} />
            <FormControl fullWidth variant="standard" sx={{ mt: '40px' }}>
                <InputLabel>비밀번호</InputLabel>
                <Input
                    type={showPassword ? 'text' : 'password'}
                    endAdornment={
                    <InputAdornment position="end">
                        <IconButton
                        onClick={() => setShowPassword(!showPassword)}
                        >
                        {showPassword ? <VisibilityOff /> : <Visibility />}
                        </IconButton>
                    </InputAdornment>
                    }
                    onChange={(event) => setPassword(event.target.value)}
                />
            </FormControl>
        </Box>
        <Box>
            <Button sx={{mb: '20px'}} fullWidth variant="contained" size='large' onClick={onLoginHandler}>로그인</Button>
            <Typography textAlign={'center'}>신규사용자 이신가요? 
                <Typography component='span' fontWeight={900} onClick={() => setLoginView(false)}> 회원가입</Typography>
            </Typography>
        </Box>
    </Box>
  )
}
