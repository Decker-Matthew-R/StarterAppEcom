import { Route, Routes } from 'react-router-dom';
import LandingPage from './src/LandingPage/LandingPage';
import { LoginPage } from './src/Login/LoginPage';
const ApplicationRoutes = () => {
  return (
    <Routes>
      <Route
        element={<LandingPage />}
        path='/'
      />

      <Route
        element={<LoginPage />}
        path='/login'
      />

      {/*<Route*/}
      {/*  element={<ViewJobApplications />}*/}
      {/*  path='/view-applications'*/}
      {/*/>*/}
    </Routes>
  );
};
export default ApplicationRoutes;
