import { render, screen, waitFor } from '@testing-library/react';
import { describe, expect, it, vi } from 'vitest';
import App from '../App';
import * as metricsClient from '../metrics/client/MetricsClient';

describe('render app', () => {
  const metricsClientMock = vi
    .spyOn(metricsClient, 'saveMetricEvent')
    .mockImplementation(() => Promise.resolve());

  const renderApp = () => render(<App />);

  it('should render app images', () => {
    renderApp();

    const countButton = screen.getByRole('button', { name: 'count is 0' });

    expect(countButton).toBeVisible();

    waitFor(() => {
      countButton.click();
      expect(metricsClientMock).toHaveBeenCalledOnce();
      expect('count is 1').toBeVisible();
    });
  });
});
