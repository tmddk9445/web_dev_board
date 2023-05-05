import { ChangeEvent, useState } from "react";

import {
  Box,
  TextField,
  FormControl,
  InputLabel,
  Input,
  InputAdornment,
  IconButton,
  FormHelperText,
} from "@mui/material";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import Visibility from "@mui/icons-material/Visibility";

import { useSignUpStore } from 'src/stores';

//          Component          //
interface FirstPageProps {
  signUpError: boolean;
}

function FirstPage({ signUpError }: FirstPageProps) {

  //          Hook          //
  const { email, password, passwordCheck } = useSignUpStore();
  const { setEmail, setPassword, setPasswordCheck } = useSignUpStore();

  const [emailMessage, setEmailMessage] = useState<string>('');
  const [passwordMessage, setPasswordMessage] = useState<string>('');
  const [passwordCheckMessage, setPasswordCheckMessage] = useState<string>('');
  const [showPassword, setShowPassword] = useState<boolean>(false);
  const [showPasswordCheck, setShowPasswordCheck] = useState<boolean>(false);

  const emailValidator = /^[A-Za-z0-9]*@[A-Za-z0-9]([-.]?[A-Za-z0-9])*\.[A-Za-z0-9]{2,3}$/;
  const passwordValidator = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!?_]).{8,20}$/;

  //          Event Handler          //
  const onEmailChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const value = event.target.value;
    const isMatched = emailValidator.test(value);
    if (isMatched) setEmailMessage('');
    else setEmailMessage('이메일 주소 포맷이 맞지 않습니다.');
    setEmail(value);
  }

  const onPasswordChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const value = event.target.value;
    const isMatched = passwordValidator.test(value);
    if (isMatched) setPasswordMessage('');
    else setPasswordMessage('영대문자 + 영소문자 + 숫자 + 특수문자(!?_)를 포함한 8-20자를 입력해주세요.');
    setPassword(value);
  }

  const onPasswordCheckChangeHandler = (event: ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    const value = event.target.value;
    const isMatched = password === value;
    if (isMatched) setPasswordCheckMessage('');
    else setPasswordCheckMessage('비밀번호가 서로 일치하지 않습니다.');
    setPasswordCheck(value);
  }

  return (
    <Box>
      <div>
        <TextField
          error
          id="outlined-error"
          label="Error"
          defaultValue="Hello World"
        />
        <TextField
          error
          label="Error"
          defaultValue="Hello World"
          helperText="Incorrect entry."
        />
      </div>
    </Box>
  );
}