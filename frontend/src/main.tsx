import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import { CssBaseline, ThemeProvider } from '@mui/material';
import { appTheme } from './themes/darkTheme';
import ApplicationRoutes from '../ApplicationRoutes';
import { BrowserRouter } from 'react-router-dom';

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ThemeProvider theme={appTheme}>
      <CssBaseline enableColorScheme />
      <BrowserRouter>
        <ApplicationRoutes />
      </BrowserRouter>
    </ThemeProvider>
  </React.StrictMode>,
);
