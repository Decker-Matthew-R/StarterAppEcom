import { useState } from 'react';
import reactLogo from '../assets/react.svg';
import viteLogo from '../assets/vite.svg';
import '../App.css';
import { Button, Typography } from '@mui/material';
import { saveMetricEvent } from '../metrics/client/MetricsClient';
import { METRIC_EVENT_TYPE } from '../metrics/model/METRIC_EVENT_TYPE';

function LandingPage() {
  const [count, setCount] = useState(0);

  return (
    <>
      <div>
        <a
          href='https://vitejs.dev'
          target='_blank'
          rel='noreferrer'
        >
          <img
            src={viteLogo}
            className='logo'
            alt='Vite logo'
          />
        </a>
        <a
          href='https://react.dev'
          target='_blank'
          rel='noreferrer'
        >
          <img
            src={reactLogo}
            className='logo react'
            alt='React logo'
          />
        </a>
      </div>
      <Typography variant={'h2'}>Vite + React</Typography>
      <div className='card'>
        <Button
          variant={'contained'}
          onClick={() => {
            setCount((count) => count + 1);
            saveMetricEvent({
              event: METRIC_EVENT_TYPE.BUTTON_CLICK,
              eventMetadata: { triggerId: 'React Button', screen: 'home' },
            })
              .then(() => {})
              .catch((error) => console.error(error));
          }}
        >
          count is {count}
        </Button>
        <Button
          color={'secondary'}
          variant={'contained'}
        >
          This Button Does Nothing
        </Button>
        <p>
          Edit <code>src/App.tsx</code> and save to test HMR
        </p>
      </div>
      <Typography
        variant={'body1'}
        className='read-the-docs'
      >
        Click on the Vite and React logos to learn more
      </Typography>
    </>
  );
}

export default LandingPage;
